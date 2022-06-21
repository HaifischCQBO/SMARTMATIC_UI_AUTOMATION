package Helpers;

import com.github.javafaker.Faker;
import com.google.zxing.NotFoundException;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import de.taimos.totp.TOTP;
import io.qameta.allure.Attachment;
import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystems;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helpers {
    private static WebDriver driver;
    private static WebElement Elemento;
    private static Faker faker;
    WebDriverWait wait;
    private int seconds = 40;

    public Helpers() {
    }

    public Helpers(WebDriver driver) {
        this.driver = driver;
        this.faker = new Faker();
        this.wait = new WebDriverWait(driver, seconds);
    }
    public WebElement returnWebElement(By by) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));

    }

    public String returnAbsolutePath(String filepath){
        File currentDirFile = new File(filepath);
       return currentDirFile.getAbsolutePath();
    }
   

    public void getURL(String url) {
        Print("Se ingresa a la URL:" + url);
        driver.get(url);
    }

    public String getXMLParameter(String Key) {
        return Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter(Key);
    }

    public Boolean isDisabled(By by) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
        if (element.isEnabled()) {
            return true;
        } else {
            return false;
        }
    }

    public void takeScreenshot(String nombre) {
        String Fecha = getDate();
        File MyScreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(MyScreenshot, new File("src/main/resources/Screenshots/" + nombre + Fecha + ".png"));
            System.out.println("Tomando Screenshot: " + nombre + " Fecha: " + Fecha);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Este metodo se usa para adjuntar la evidencia en el reporte Allure.
    @Attachment(value = "TEST FAIL: Screenshot Evidence", type = "image/png")
    public byte[] takeScreenshotAllureFAIL() {
        // Take a screenshot as byte array and return
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "TEST PASS: Screenshot Evidence", type = "image/png")
    public byte[] takeScreenshotAllurePASS() {
        // Take a screenshot as byte array and return
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    public WebElement getElement(By by) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public Boolean ElementExist(By by) {
        WebElement element = null;
        element = new WebDriverWait(driver, 120).until(ExpectedConditions.presenceOfElementLocated(by));
        if (element != null) {
            return true;
        } else {
            return false;
        }
    }
    public Boolean ElementIsEnabled(By by) {
        WebElement element = null;
        element = new WebDriverWait(driver, 120).until(ExpectedConditions.presenceOfElementLocated(by));
        if (element.isEnabled()) {
            return true;
        } else {
            return false;
        }
    }

    public String getDate() {
        Date date = new Date();
        DateFormat hourdateFormat = new SimpleDateFormat("HH.mm.ss dd-MM-yyyy");
        String valor = hourdateFormat.format(date);
        return valor;
    }

    public Boolean ElementDoNotExist(By by) {
        if (new WebDriverWait(driver, 120).until(ExpectedConditions.invisibilityOfElementLocated(by))) {
            return true;
        } else {
            return false;
        }
    }

    public void Wait(int secs) {
        try {
            Thread.sleep(secs * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void Print(String texto) {
        System.out.println(texto + "\r\n");
    }

    public void DragAndDrop(){
        Actions builder = new Actions(driver);

        WebElement from = driver.findElement(By.id("draggable"));

        WebElement to = driver.findElement(By.id("droppable"));
        //Perform drag and drop

    }
    public String GetAbsolutePath(String path){
        String absolutePath = FileSystems.getDefault()
                .getPath(path)
                .normalize()
                .toAbsolutePath()
                .toString();
        return absolutePath;
    }
    public void Click(By by) {

        // Espera dinamica
        wait.until(ExpectedConditions.elementToBeClickable(by));
        // ----------------------------------------------------

        WebElement elemento = driver.findElement(by);

        // ----------------------------------------------------

        elemento.click();
        Print("Se realiza Click a Elemento:" + by);
    }
    public void ClickUntilEnabled(By by) {
        // Espera dinamica
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
            wait.until((ExpectedCondition<Boolean>) driver -> element.isEnabled());

        element.click();
        Print("Se realiza Click a Elemento:" + by);
    }

    public void Click(WebElement elemento) {

        wait.until(ExpectedConditions.elementToBeClickable(elemento));
        // ----------------------------------------------------

        elemento.click();
        Print("Se realiza Click a Elemento:" + elemento);
    }

    public void ClickJS(By by) {
        WebElement element = driver.findElement(by);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public void ClickJS(WebElement webElement) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", webElement);
    }

    public void Pause(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendText(By by, String text) {
        WebElement elemento = wait.until(ExpectedConditions.elementToBeClickable(by));
        elemento.sendKeys(text);
        Print("Se envia texto " + text + " a Elemento:" + by);
    }
    public void sendText(By by, String text, int clear) {
        WebElement elemento = wait.until(ExpectedConditions.presenceOfElementLocated(by));
        elemento.clear();
        elemento.sendKeys(text);
        Print("Se envia texto " + text + " a Elemento:" + by);
    }
    public void sendImage(By by, String path) {
        WebElement elemento = wait.until(ExpectedConditions.presenceOfElementLocated(by));
        elemento.sendKeys(path);
        Print("Se envia ruta de imagen:  " + path + " a Elemento:" + by);
    }

    public void changeCheckboxState(By by, Boolean final_state){
        WebElement elemento = driver.findElement(by);
        //DETERMINE THE STATE IT SHOULD BE
        if(final_state) {
            Print("FINAL STATE IS : " + true);
            //IF TRUE CHECKBOX SHOULD BE SELECTED BY THE END.
            if (!elemento.isSelected()) {
                Print("IF FROM ELEMENT STATE: "+ elemento.isSelected());
                //IF THE ELEMENT ISNT SELECTED, PROCEED TO CHECK IT
                ClickJS(elemento);
            }
        }else {
            //IF FALSE CHECKBOX SHOULD BE DESELECTED BY THE END.
            Print("FINAL STATE IS : " + false);
            if (elemento.isSelected()) {
                Print("IF FROM ELEMENT STATE: "+ elemento.isSelected());
                //IF IS SELECTED, DESELECTED.
                ClickJS(elemento);
            }
        }


    }
    public boolean compare_text(String origin, String subject) {
        return origin.equals(subject);
    }

    public boolean contains_text(String origin, String subject) {
        Print("Se compara Origen: " + origin + " con Objetivo: " + subject);
        return origin.contains(subject);
    }

    public String getText(By by) {
        WebElement elemento = wait.until(ExpectedConditions.presenceOfElementLocated(by));
        String texto_extraido = elemento.getText();
        Print("Se extrae texto: " + texto_extraido + " del elemento: " + by);
        return texto_extraido;
    }

    public String getText(WebElement elemento) {
        String texto_extraido = elemento.getText();
        Print("Se extrae texto: " + texto_extraido + " del elemento: " + elemento);
        return texto_extraido;
    }

    // ---------------FAKER METHODS-------------------------------------
    public String fake_first_name() {
        return faker.name().firstName();
    }

    public String fake_email_address() {
        return faker.internet().emailAddress();
    }

    public String fake_address() {
        return faker.address().streetAddress();
    }

    // ---------------FAKER METHODS-------------------------------------
}
