package Helpers;


import com.bastiaanjansen.otp.TOTP;
import com.github.javafaker.Faker;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import org.apache.commons.codec.binary.Base64;
import org.jboss.aerogear.security.otp.api.Base32;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class topt_decode extends Helpers {

    private static WebDriver driver;
    private static WebElement Elemento;
    private static Faker faker;
    WebDriverWait wait;
    private int seconds = 40;

    public topt_decode() {
    }

    public topt_decode(WebDriver driver) {
        this.driver = driver;
        this.faker = new Faker();
        this.wait = new WebDriverWait(driver, seconds);
    }

    public byte[] getBytesBase64FromBlobURI_By(By by) {
        Pause(3);
        WebElement Elemento = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        URI imageURI = null;
        String uri = Elemento.getAttribute("src");
        String script = " "
                + "var uri = arguments[0];"
                + "var callback = arguments[1];"
                + "var toBase64 = function(buffer){for(var r,n=new Uint8Array(buffer),t=n.length,a=new Uint8Array(4*Math.ceil(t/3)),i=new Uint8Array(64),o=0,c=0;64>c;++c)i[c]='ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/'.charCodeAt(c);for(c=0;t-t%3>c;c+=3,o+=4)r=n[c]<<16|n[c+1]<<8|n[c+2],a[o]=i[r>>18],a[o+1]=i[r>>12&63],a[o+2]=i[r>>6&63],a[o+3]=i[63&r];return t%3===1?(r=n[t-1],a[o]=i[r>>2],a[o+1]=i[r<<4&63],a[o+2]=61,a[o+3]=61):t%3===2&&(r=(n[t-2]<<8)+n[t-1],a[o]=i[r>>10],a[o+1]=i[r>>4&63],a[o+2]=i[r<<2&63],a[o+3]=61),new TextDecoder('ascii').decode(a)};"
                + "var xhr = new XMLHttpRequest();"
                + "xhr.responseType = 'arraybuffer';"
                + "xhr.onload = function(){ callback(toBase64(xhr.response)) };"
                + "xhr.onerror = function(){ callback(xhr.status) };"
                + "xhr.open('GET','" + uri + "');"
                + "xhr.send();";

        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        String result = (jsExecutor.executeAsyncScript(script, uri)).toString();
        return Base64.decodeBase64(result);
    }

    public String decodeQRCode(byte[] qrCodeImage) throws IOException, NotFoundException {
        Result result = null;
        BufferedImage bufferedImage;
        bufferedImage = ImageIO.read(new ByteArrayInputStream(qrCodeImage));

        LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        result = new MultiFormatReader().decode(bitmap);
        String final_uri = result.getText();
        System.out.println(final_uri);
        return final_uri;
    }


    public String janseenTOTP(String otpKeyStr) throws URISyntaxException {
        URI uri = new URI(otpKeyStr);
        TOTP totp = TOTP.fromURI(uri);
        System.out.println(totp.now());
        return totp.now();
    }

    public String GenerateCodeMFA(By by) throws NotFoundException, IOException, Base32.DecodingException, URISyntaxException {

        //READ THE WEBELEMENT AND GET THE BASE64 CODE
        byte[] base64 = getBytesBase64FromBlobURI_By(by);
        //DECODE THE BASE64 TO GET THE URI
        String uri_decoded = decodeQRCode(base64);
        String code = janseenTOTP(uri_decoded);

        return code;
    }
}
