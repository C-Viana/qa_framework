package common;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.opentest4j.AssertionFailedError;

import config.Config;
import engine.Driver;
import reporter.Reports;


public class SeleniumUtils {
	
    private static Point chromePosition;
    private static Dimension chromeDimensions;

    public SeleniumUtils() {
        throw new IllegalStateException("Erro para iniciar a classe General.");
    }

    /**
     * Cria os diretórios informados através de um caminho definido em uma string.
     * Se os diretórios já existirem, nenhuma ação será realizada.
     * <br>
     * Exemplo: <br>
     * path = "D:/PDI/template-automacao-web/uploads"
     * <br>
     * 
     * @param path
     */
    public static void createFolders(String path) {
        File f = new File(path);
        if (!f.exists()) {
            f.mkdirs();
        }
    }

    /**
     * A partir do locator informado, retornará uma lista de todos os elementos
     * encontrados que atendam à referência.
     * <br>
     * 
     * @param locator
     * @return java.util.List<WebElement>
     */
    public static List<WebElement> getElements(By locator) {
        return Driver.get().findElements(locator);
    }

    /**
     * A partir do locator informado, retornará o texto do elemento encontrado que
     * atenda à referência.
     * <br>
     * 
     * @param locator
     * @return String
     */
    public static String getTextFromElement(By locator) {
        return Driver.get().findElement(locator).getText().trim();
    }

    /**
     * Para interações web em que for necessário realizar o preenchimento de campos
     * via Javascript, <br>
     * informe neste método o elemento web (ex.: input) e o valor a ser inserido.
     * <br>
     * 
     * @param element
     * @param value
     */
    public static void sendKeysByJavascript( WebElement element, String value ) {
        ((JavascriptExecutor) Driver.get()).executeScript("arguments[0].value = '" + value + "';", element);
    }

    /**
     * Para interações web em que for necessário realizar um clique em um componente
     * via
     * Javascript, <br>
     * informe neste método o elemento web (ex.: button) a ser clicado.
     * <br>
     * 
     * @param element
     * @param value
     */
    public static void clickByJavascript(WebElement element) {
        ((JavascriptExecutor) Driver.get()).executeScript("arguments[0].click()", element);
    }

    /**
     * Através de um WebElement informado, utiliza a classe
     * selenium.interactions.Actions para mover o mouse às coordenadas x e y desta
     * referência e, então, realizar um clique nesta posição.
     * <br>
     * 
     * @param element
     */
    public static void clickByCoordinates(WebElement element) {
        Point coords = SeleniumUtils.getElementCenter(element);
        Actions act = new Actions(Driver.get());
        act.moveToElement(element, 0, 0).build().perform();
        act.moveByOffset(coords.x, coords.y).click().build().perform();
        act = null;
    }

    /**
     * Informar as coordenadas x e y de um ponto do viewport para executar um clique
     * através da classe selenium.interactions.Actions.
     * <br>
     * 
     * @param posX
     * @param posY
     */
    public static void clickByCoordinates(int posX, int posY) {
        Actions act = new Actions(Driver.get());
        act.moveByOffset(posX, posY).click().build().perform();
        act = null;
    }

    /**
     * Para interações web em que for necessário mover a tela para visualizar um
     * elemento, este método <br>
     * realizará um scroll down/up para tentar colocar o elemento dentro da área do
     * viewport. <br>
     * Em algumas situações, pode ser necessário utilizar este recurso duas vezes.
     * <br>
     * 
     * @param element
     */
    public static void scrollIntoByJavascript(WebElement element) {
        ((JavascriptExecutor) Driver.get()).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void scrollBottomPage() {
        ((JavascriptExecutor) Driver.get()).executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    /**
     * Retorna os atributos HTML que estejam implementados na tag deste WebElement.
     * <br>
     * 
     * @param element
     * @return
     */
    public static String getTagAttributesByJavaScript(WebElement element) {
        String test  = ((JavascriptExecutor) Driver.get()).executeScript("return arguments[0].attributes;", element).toString();
        return test;
    }

    /**
     * Atribui à variável General.chromePosition as coordenadas atuais da posição
     * da janela do browser.
     * <br>
     * 
     * @param posX
     * @param posY
     */
    public static void setChromePositionLocalVariable() {
        chromePosition = Driver.get().manage().window().getPosition();
    }

    /**
     * Atribui à variável General.chromeDimensions as coordenadas atuais das dimensões
     * da janela do browser.
     * <br>
     * 
     * @param width
     * @param height
     */
    public static void setChromeDimensionsLocalVariable() {
        chromeDimensions = Driver.get().manage().window().getSize();
    }

    /**
     * Retorna um objeto Point com a atual posição da janela do browser na tela.
     * <br>
     * 
     * @return
     */
    public static Point getChromePosition() {
    	if( chromePosition == null)
    		setChromePositionLocalVariable();
        return chromePosition;
    }

    /**
     * Retorna um objeto Dimension com o atual tamanho da janela do browser na tela.
     * <br>
     * 
     * @return
     */
    public static Dimension getChromeDimensions() {
    	if( chromeDimensions == null)
    		setChromeDimensionsLocalVariable();
        return chromeDimensions;
    }

    /**
     * Realiza uma atualização na tela, semelhante ao comando F5 para a maioria dos
     * navegadores.
     * <br>
     * 
     */
    public static void refreshPage() {
        Driver.get().navigate().refresh();
    }

    /**
     * Dado um WebElement, retorna um objeto Point com as coordenadas x e y que
     * representam o ponto central deste elemento.
     * <br>
     * 
     * @param element
     * @return
     */
    public static Point getElementCenter( WebElement element ) {
        int width = element.getSize().width;
        int posX = element.getLocation().x;

        int height = element.getSize().height;
        int posY = element.getLocation().y;
        return new Point( posX+(width/2), posY+(height/2) );
    }

    /**
     * Através da classe selenium.interactions.Actions, realiza a ação de arrastar
     * um objeto origem para a posição de um objeto destino.
     * <br>
     * Este método pode não funcionar corretamente devido a limitações da própria
     * classe Actions.
     * <br>
     * Se esse for o caso, considere a viabilidade de utilizar o método
     * dragAndDropSikulix().
     * <br>
     * 
     * @param origin
     * @param destination
     */
    public static void dragElementTo( WebElement origin, WebElement destination ) {
        new Actions(Driver.get()).dragAndDrop(origin, destination).build().perform();
    }

    /**
     * Através da classe selenium.interactions.Actions, simula o posicionamento do
     * mouse sobre um WebElement.
     * <br>
     * Este método não move o cursor do mouse ao objeto informado, mas apenas irá
     * ativar o efeito "hover" sobre ele.
     * <br>
     * 
     * @param element
     */
    public static void hoverElement(WebElement element) {
        try {
            new Actions(Driver.get()).moveToElement(element).build().perform();
        } catch (Exception e) {
            Logger.getLogger(SeleniumUtils.class.getName()).log(Level.WARNING,
                    "Erro para utilizar hoverElement(WebElement element)", e);
            throw e;
        }
    }

    /**
     * Informe uma URL para que o Selenium abra essa página e aguarde que essa
     * esteja pronta.
     * <br>
     * 
     * @param url
     */
    public static void goTo(String url) {
        Driver.get().get(url);
    }
    
    /**
     * Informe um tempo em segundos para que a aplicação espere de maneira
     * incondicional para executar a próxima ação.
     * Caso o parâmetro de tempo seja nulo, será utilizado o valor DEFAULT_WAIT_TIME
     */
    public static void waitFor( Integer timeInSeconds ) {
        try {
            int defaultWaitTime = Integer.parseInt(Config.get("test.timeout.default"));
        	TimeUnit.SECONDS.sleep( (timeInSeconds == null) ? defaultWaitTime : timeInSeconds );
//            Thread.sleep( (timeInSeconds == null ) ? StaticResources.DEFAULT_WAIT_TIME : timeInSeconds*1000 );
            }
        catch (InterruptedException e) {
            Logger.getLogger(SeleniumUtils.class.getName()).log(Level.WARNING, "Erro para utilizar Thread.sleep()", e);
        }
     }

    /**
     * Especifique um WebElement e um tempo em segundos para aguardar até que este
     * elemento esteja disponível em tela para ser encontrado pelo Selenium.
     * Caso o elemento esteja disponível antes do tempo especificado, o teste
     * prosseguirá antes do tempo máximo especificado.
     * Caso o parâmetro de tempo seja nulo, será utilizado o valor DEFAULT_WAIT_TIME
     * <br>
     * 
     * @param element
     * @param timeInSeconds
     */
    public static void waitToBeVisible( WebElement element, Integer timeInSeconds ) {
        int defaultWaitTime = Integer.parseInt(Config.get("test.timeout.default"));
        new WebDriverWait(Driver.get(), Duration.ofSeconds((timeInSeconds == null) ? defaultWaitTime
                : timeInSeconds)).until( ExpectedConditions.visibilityOf(element) );
    }

    /**
     * Especifique um locator (By:xpath, By:css, etc) e um tempo em segundos para
     * aguardar até que este elemento esteja disponível em tela para ser encontrado
     * pelo Selenium.
     * Caso o elemento esteja disponível antes do tempo especificado, o teste
     * prosseguirá antes do tempo máximo especificado.
     * Caso o parâmetro de tempo seja nulo, será utilizado o valor DEFAULT_WAIT_TIME
     * <br>
     * 
     * @param locator
     * @param timeInSeconds
     */
    public static void waitToBeVisible(By locator, Integer timeInSeconds) {
        int defaultWaitTime = Integer.parseInt(Config.get("test.timeout.default"));
        new WebDriverWait(Driver.get(), Duration.ofSeconds((timeInSeconds == null) ? defaultWaitTime
                : timeInSeconds)).until(ExpectedConditions.visibilityOf(Driver.get().findElement(locator)));
    }

    /**
     * Informe um WebElemento e um tempo em segundos para aguardar até que este
     * elemento esteja disponível em tela para ser encontrado
     * pelo Selenium.
     * <br>
     * Este método realizará a espera ignorando as exceções
     * StaleElementReferenceException, NoSuchElementException e NotFoundException.
     * <br>
     * 
     * @param element
     * @param timeInSeconds
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static Object waitToBeVisibleIgnoringExceptions(WebElement element, Integer timeInSeconds) {
        List<Class> exceptions = new ArrayList<Class>();
        exceptions.add(StaleElementReferenceException.class);
        exceptions.add(NoSuchElementException.class);
        exceptions.add(NotFoundException.class);

        int defaultWaitTime = Integer.parseInt(Config.get("test.timeout.default"));
        try {
            return new FluentWait(Driver.get())
                    .withTimeout(Duration.ofSeconds((timeInSeconds == null) ? defaultWaitTime : timeInSeconds))
                    .pollingEvery(Duration.ofSeconds(1))
                    .ignoreAll(exceptions).until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Retorna true se o WebElement informado tiver o atributo "display" diferente
     * de nulo/none.
     * Caso contrário, retornará false.
     * <br>
     * 
     * @param element
     * @return
     */
    public static boolean elementExists(WebElement element) {
        try {
            return element.isDisplayed();
        }
        catch (StaleElementReferenceException | NoSuchElementException ex) {
            return false;
        }
    }

    /**
     * Retorna um objeto web Alert após a espera condicionada.
     * Caso o tempo informado seja null, será considerado o tempo definido como
     * padrão.
     * <br>
     * 
     * @param timeInSeconds
     * @return
     */
    public static Alert getAlert(Integer timeInSeconds) {
        int defaultWaitTime = Integer.parseInt(Config.get("test.timeout.default"));
        return new WebDriverWait(Driver.get(), Duration.ofSeconds((timeInSeconds == null) ? defaultWaitTime
                : timeInSeconds)).until(ExpectedConditions.alertIsPresent() );
    }

    /**
     * Retorna o foco da página para o contexto principal; saindo de frames/iframes que estejam presentes.
     */
    public static void setDefaultContext() {
        Driver.get().switchTo().defaultContent();
    }

    /**
     * Entra no contexto de um frame/iframe a partir do próprio WebElement que o
     * identifique.
     * <br>
     * 
     * @param frame
     */
    public static void setContext(WebElement frame) {
        Driver.get().switchTo().frame(frame);
    }

    /**
     * Entra no contexto de um frame/iframe pelo índice que o identifique.
     * <br>
     * 
     * @param index
     */
    public static void setContext(int index) {
        Driver.get().switchTo().frame(index);
    }

    /**
     * Através de recursos da classe java.net, retorna o status code ao tentar
     * realizar uma conexão com a URL informada.
     * <br>
     * É realizado um método GET sobre a URL e o status code é retornado como
     * inteiro.
     * <br>
     * Considere utilizar este método para validar o acesso a uma página antes de
     * abri-la ou para validar a integridade de recursos da página de teste
     * (imagens, arquivos de som, etc.).
     * <br>
     * 
     * @param targetUrl
     * @return
     * @throws URISyntaxException 
     */
    public static int getStatusCodeFromURL(String targetUrl) throws URISyntaxException {
        try {
            HttpURLConnection conn = (HttpURLConnection) new URI(targetUrl).toURL().openConnection();
            conn.setRequestMethod("GET");
            return conn.getResponseCode();
        }
        catch (MalformedURLException e) {
            Logger.getLogger(SeleniumUtils.class.getName()).log(Level.WARNING,
                    "Erro para utilizar getStatusCodeFromURL(String targetUrl)", e);
            return 0;
        }
        catch (IOException e) {
            Logger.getLogger(SeleniumUtils.class.getName()).log(Level.WARNING,
                    "Erro para utilizar getStatusCodeFromURL(String targetUrl)", e);
            return 0;
        }
    }

    /**
     * Retorna um objeto java.awt.image.BufferedImage a partir de um WebElement em
     * tela.
     * <br>
     * A imagem será capturada a partir das dimensões do objeto isolado.
     * <br>
     * 
     * @param element
     * @return
     */
    public static BufferedImage getImageFromObject( WebElement element ) {
        BufferedImage bi = new BufferedImage(element.getRect().width, element.getRect().height, BufferedImage.TYPE_INT_ARGB);
        try {
            bi = ImageIO.read(element.getScreenshotAs(OutputType.FILE));
            return bi;
        } catch (WebDriverException | IOException e) {
            Logger.getLogger(SeleniumUtils.class.getName()).log(Level.WARNING, "Erro para utilizar getImageFromObject(WebElement element)", e);
            return null;
        }
    }
    
    public static String getBackgroundColorAsHex(WebElement element) {
    	return Color.fromString(element.getCssValue("background-color")).asHex();
    }

    /**
     * Através da classe selenium.interactions.Actions, simula um clique com o botão
     * direito do mouse.
     * <br>
     * 
     * @param element
     */
    public static void rightClickOnElement(WebElement element) {
        new Actions(Driver.get()).contextClick(element).perform();
    }

	public static void validateEquals(Object expected, Object current, String logMessage) {
		try {
			Assertions.assertEquals(expected, current);
			Reports.get().setPass(logMessage, true);
		} catch (AssertionFailedError e) {
			Reports.get().setFail("Test failed due result error.<br/>"+logMessage+".<br/>Expected: {"+expected+"} | Current: {"+current+"}", true);
			throw e;
		}
	}

	public static void validateTextNotBlank(String value, String logMessage, boolean screenshot) {
		try {
			Assertions.assertTrue(value != null && !value.isBlank());
			Reports.get().setPass(logMessage, screenshot);
		} catch (AssertionFailedError e) {
			Reports.get().setFail("Text validation.<br/>"+logMessage+".", true);
			throw e;
		}
	}

    public static void validateElementIsDisplayed(WebElement element, String logMessage) {
        try {
			waitToBeVisible(element, null);
            validateEquals(true, SeleniumUtils.elementExists(element), logMessage);
		} catch (AssertionFailedError e) {
			Reports.get().setFail("Test failed because element was not visible.<br/>"+logMessage+".<br/>", true);
			throw e;
		}
    }

    public static WebElement getElementByText(String visibleText) {
        try {
            return Driver.get().findElement(By.xpath("//*[text()='"+visibleText+"']"));
        } catch (Exception e) {
            return null;
        }
    }

    public static List<WebElement> getAllElements(By locator) {
        try {
            return Driver.get().findElements(locator);
        } catch (Exception e) {
            return null;
        }
    }

    public static int countElements(By locator) {
        try {
            return Driver.get().findElements(locator).size();
        } catch (Exception e) {
            return -1;
        }
    }

    public static int countElements(String visibleText) {
        return countElements(By.xpath("//*[text()='"+visibleText+"']"));
    }

    public static void selectByText(WebElement select, String visibleText) {
        try {
            new Select(select).selectByVisibleText(visibleText);
            Reports.get().setPass("Selected option: " + visibleText, true);
        } catch (Exception e) {
            Reports.get().setFail("Failed to select option " + visibleText, true);
			throw e;
        }
    }

    public static void selectByValue(WebElement select, String value) {
        try {
            new Select(select).selectByValue(value);
            Reports.get().setPass("Selected option by value: " + value, true);
        } catch (Exception e) {
            Reports.get().setFail("Failed to select option by value " + value, true);
			throw e;
        }
    }

    public static void selectByIndex(WebElement select, int index) {
        try {
            new Select(select).selectByIndex(index);
            Reports.get().setPass("Selected option by index: " + index, true);
        } catch (Exception e) {
            Reports.get().setFail("Failed to select option by index " + index, true);
			throw e;
        }
    }

    public static void returnToMainWindow() {
        Driver.get().close();
        Object[] handles = Driver.get().getWindowHandles().toArray();
        Driver.get().switchTo().window((String)handles[0]);
        Reports.get().setPass("Return to main window", true);
    }

    public static void changeWindow(int index) {
        try {
            Object[] handles = Driver.get().getWindowHandles().toArray();
            Driver.get().switchTo().window((String)handles[index]);
            Reports.get().setPass("Changed to window on index: " + index, true);
        } catch (Exception e) {
            Reports.get().setFail("Failed to change window on index " + index, true);
			throw e;
        }
    }

    public static boolean timer(LocalDateTime startTime, LocalDateTime finalTime) {
        return startTime.isBefore(finalTime);
    }

}
