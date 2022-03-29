package bb.dd.dp.zadanie1;

import bb.dd.dp.impl.NumericCTEncryptor;
import bb.dd.dp.impl.RailFenceEncryptor;
import bb.dd.dp.impl.StringCTEncryptor;
import bb.dd.dp.impl2.LSFRImpl;
import bb.dd.dp.impl2.SynchronousStreamCipherEncryptor;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.util.Duration;

import java.math.BigInteger;
import java.util.Arrays;


// rf - Rail fence
// pm - Przestawienie macierzowe
// pms - Przestawienie macierzowe słowo
public class HelloController {

    @FXML
    void initialize() {
        final Tooltip tooltip = new Tooltip();
        tooltip.setText("Click to copy!");
        tooltip.setShowDelay(new Duration(250));
        rfOutput.setTooltip(tooltip);
        pmOutput.setTooltip(tooltip);
        pmsOutput.setTooltip(tooltip);
    }


    @FXML
    private Button pmDecode;

    @FXML
    private TextField pmDepth;

    @FXML
    private Button pmEncode;

    @FXML
    private TextArea pmInput;

    @FXML
    private TextField pmKey;

    @FXML
    private Label pmOutput;

    @FXML
    private Button pmsDecode;

    @FXML
    private TextField pmsDepth;

    @FXML
    private Button pmsEncode;

    @FXML
    private TextArea pmsInput;

    @FXML
    private TextField pmsKey;

    @FXML
    private Label pmsOutput;

    @FXML
    private Button rfDecode;

    @FXML
    private Button rfEncode;

    @FXML
    private TextArea rfInput;

    @FXML
    private TextField rfKey;

    @FXML
    private Label rfOutput;

    @FXML
    private Button lsfrNew;

    @FXML
    private Button lsfrNext;

    @FXML
    private Label lsfrOutput;

    @FXML
    private TextField lsfrPoly;

    @FXML
    private Label lsfrPolyOutput;

    @FXML
    private Button sccDecode;

    @FXML
    private Button sccEncode;

    @FXML
    private TextArea sccInput;

    @FXML
    private Label sccOutput;

    @FXML
    private TextField sccPoly;

    @FXML
    private Label sccPolyOutput;

    @FXML
    private TextField sccSeed;




    private RailFenceEncryptor railFenceEncryptor;
    
    private NumericCTEncryptor numericCTEncryptor;
    
    private StringCTEncryptor stringCTEncryptor;

    private LSFRImpl lsfr;

    private SynchronousStreamCipherEncryptor synchronousStreamCipherEncryptor;
    //Zadanie 1
    //Rail fence - szyfrowanie
    @FXML
    public void rfEncodeClick() {
        if (rfInput.getText().isBlank() || railFenceEncryptor == null) {
            showErrorDialog("Wszystkie pola muszą być wypełnione");
            return;
        }
        
        rfOutput.setText(railFenceEncryptor.encrypt(rfInput.getText()));
    }

    //Rail fence - deszyfrowanie
    @FXML
    public void rfDecodeClick() {
        if (rfInput.getText().isBlank() || railFenceEncryptor == null) {
            showErrorDialog("Wszystkie pola muszą być wypełnione");
            return;
        }
        
        rfOutput.setText(railFenceEncryptor.decrypt(rfInput.getText()));
    }

    //Przestawienie macierzowe - szyfrowanie
    @FXML
    public void pmEncodeClick() {
        if (pmInput.getText().isBlank() || numericCTEncryptor == null) {
            showErrorDialog("Wszystkie pola muszą być wypełnione");
            return;
        }
        
        pmOutput.setText(numericCTEncryptor.encrypt(pmInput.getText()));
    }

    //Przestawienie macierzowe - deszyfrowanie
    @FXML
    public void pmDecodeClick() {
        if (pmInput.getText().isBlank() || numericCTEncryptor == null) {
            showErrorDialog("Wszystkie pola muszą być wypełnione");
            return;
        }
        
        pmOutput.setText(numericCTEncryptor.decrypt(pmInput.getText()));
    }
    
    //Przestawienie macierzowe słowo - szyfrowanie
    @FXML
    public void pmsEncodeClick() {
        if (pmsInput.getText().isBlank() || stringCTEncryptor == null) {
            showErrorDialog("Wszystkie pola muszą być wypełnione");
            return;
        }
        
        pmsOutput.setText(stringCTEncryptor.encrypt(pmsInput.getText()));
    }

    //Przestawienie macierzowe słowo - deszyfrowanie
    @FXML
    public void pmsDecodeClick() {
        if (pmsInput.getText().isBlank() || stringCTEncryptor == null) {
            showErrorDialog("Wszystkie pola muszą być wypełnione");
            return;
        }
    
        pmsOutput.setText(stringCTEncryptor.decrypt(pmsInput.getText()));
    }
    // Popup do walidacji
    private void showErrorDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setHeaderText(null);
        alert.setContentText(message);
        
        alert.showAndWait();
    }
    
    
    @FXML
    public void onParamsChangedRf() {
        var keyText = rfKey.getText();
        if (keyText.isBlank()) {
            railFenceEncryptor = null;
            return;
        }
        
        if (!keyText.matches("[0-9]{0,5}")) {
            rfKey.setText("");
            showErrorDialog("Zły format klucza");
            return;
        }
        
        int key = Integer.parseInt(keyText);
        railFenceEncryptor = new RailFenceEncryptor(key);
    }
    
    @FXML
    public void onParamsChangedPm() {
        var keyText = pmKey.getText();
        var depthText = pmDepth.getText().strip();
        if (keyText.isBlank() || depthText.isBlank()) {
            numericCTEncryptor = null;
            return;
        }
        
        
        int[] key = Arrays.stream(keyText.split("([,-.;:|]|\\s){0,5}"))
                .filter(s -> s.matches("[0-9]+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        
        if (key.length < 1) {
            numericCTEncryptor = null;
            return;
        }
        
        if (!depthText.matches("[0-9]{0,5}")) {
            pmDepth.setText("");
            showErrorDialog("Zły format klucza");
            numericCTEncryptor = null;
            return;
        }
        
        int depth = Integer.parseInt(depthText);
        numericCTEncryptor = new NumericCTEncryptor(key, depth);
    }
    
    @FXML
    public void onParamsChangedPms() {
        var keyText = pmsKey.getText();
        var depthText = pmsDepth.getText();
        if (keyText.isBlank() || depthText.isBlank()) {
            stringCTEncryptor = null;
            return;
        }
        
        if (!depthText.matches("[0-9]{0,5}")) {
            pmsDepth.setText("");
            showErrorDialog("Zły format klucza");
            stringCTEncryptor = null;
            return;
        }
        
        int depth = Integer.parseInt(depthText);
        stringCTEncryptor = new StringCTEncryptor(keyText, depth);
    }

    // Obsługa copiowania z lable
    @FXML
    public void onClickLabelRf() {
        toClipboard(rfOutput.getText());
    }
    
    @FXML
    public void onClickLabelPm() {
        toClipboard(pmOutput.getText());
    }
    
    @FXML
    public void onClickLabelPms() {
        toClipboard(pmsOutput.getText());
    }

    @FXML
    public void onClickLabelScc() {
        toClipboard(sccOutput.getText());
    }
    
    public void toClipboard(String clip) {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();
        content.putString(clip);
        clipboard.setContent(content);
    }

    //Zadanie 2
    //FIXME Brak generowania seed. Dla podanego seed'a wynik jest błędny
    @FXML
    public void onClickNewSeed(){
        // towrzenie wielomianu do wyświetlania
        String[] polyStr = lsfrPoly.getText().split(",");
        String poly = "1 + x^" + String
                .join(" + x^",polyStr);
        lsfrPolyOutput.setText(poly);


        lsfr = new LSFRImpl(Arrays
                .stream(polyStr)
                .mapToInt(Integer::parseInt)
                .toArray());
        BigInteger b = new BigInteger("16");
        lsfr.setSeed(b);
        lsfrOutput.setText(lsfr.nextState().toString(2));

    }

    @FXML
    public void onClickNext(){
        lsfrOutput.setText(lsfr.nextState().toString(2));
    }

     public void sccEncodeClick(){
        String[] polyStr = sccPoly.getText().split(",");
        synchronousStreamCipherEncryptor = new SynchronousStreamCipherEncryptor(Arrays
                .stream(polyStr)
                .mapToInt(Integer::parseInt)
                .toArray());

        BigInteger b = new BigInteger("16");
        synchronousStreamCipherEncryptor.setSeed(b);

        sccSeed.setText(b.toString());

        sccOutput.setText(synchronousStreamCipherEncryptor.encrypt(sccInput.getText()));
     }

    public void sccDecodeClick(){
        String[] polyStr = sccPoly.getText().split(",");
        synchronousStreamCipherEncryptor = new SynchronousStreamCipherEncryptor(Arrays
                .stream(polyStr)
                .mapToInt(Integer::parseInt)
                .toArray());

        synchronousStreamCipherEncryptor.setSeed(new BigInteger(sccSeed.getText()));

        sccOutput.setText(synchronousStreamCipherEncryptor.decrypt(sccInput.getText()));
    }

}

