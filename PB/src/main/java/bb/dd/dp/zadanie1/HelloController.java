package bb.dd.dp.zadanie1;

import bb.dd.dp.impl.NumericCTEncryptor;
import bb.dd.dp.impl.RailFenceEncryptor;
import bb.dd.dp.impl.StringCTEncryptor;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.util.Duration;

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

    //Rail fence - szyfrowanie
    @FXML
    public void rfEncodeClick(){
        if(rfInput.getText().equals("") || rfKey.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Wszystkie pola muszą być wypełnione");

            alert.showAndWait();
            return;
        }
        RailFenceEncryptor railFenceEncryptor = new RailFenceEncryptor(Integer.parseInt(rfKey.getText()));
        rfOutput.setText(railFenceEncryptor.encrypt(rfInput.getText()));
    }

    //Rail fence - deszyfrowanie
    @FXML
    public void rfDecodeClick(){
        if(rfInput.getText().equals("") || rfKey.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Wszystkie pola muszą być wypełnione");

            alert.showAndWait();
            return;
        }
        RailFenceEncryptor railFenceEncryptor = new RailFenceEncryptor(Integer.parseInt(rfKey.getText()));
        rfOutput.setText(railFenceEncryptor.decrypt(rfInput.getText()));
    }

    //Przestawienie macierzowe - szyfrowanie
    @FXML
    public void pmEncodeClick(){
        if(pmKey.getText().equals("") || pmDepth.getText().equals("") || pmOutput.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Wszystkie pola muszą być wypełnione");

            alert.showAndWait();
            return;
        }

        String pmKeyString = pmKey.getText();
        int[] key = Arrays.stream(pmKeyString.split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
        NumericCTEncryptor numericCTEncryptor = new NumericCTEncryptor(key,Integer.parseInt(pmDepth.getText()));
        pmOutput.setText(numericCTEncryptor.encrypt(pmInput.getText()));
    }

    //Przestawienie macierzowe - deszyfrowanie
    @FXML
    public void pmDecodeClick(){

        if(pmKey.getText().equals("") || pmDepth.getText().equals("") || pmOutput.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Wszystkie pola muszą być wypełnione");

            alert.showAndWait();
            return;
        }
        String pmKeyString = pmKey.getText();
        int[] key = Arrays.stream(pmKeyString.split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
        NumericCTEncryptor numericCTEncryptor = new NumericCTEncryptor(key,Integer.parseInt(pmDepth.getText()));
        pmOutput.setText(numericCTEncryptor.decrypt(pmInput.getText()));
    }

    //Przestawienie macierzowe słowo - szyfrowanie
    @FXML
    public void pmsEncodeClick(){

        if(pmsKey.getText().equals("") || pmsDepth.getText().equals("") || pmsOutput.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Wszystkie pola muszą być wypełnione");

            alert.showAndWait();
            return;
        }

        StringCTEncryptor stringCTEncryptor = new StringCTEncryptor(pmsKey.getText(), Integer.parseInt(pmsDepth.getText()));
        pmsOutput.setText(stringCTEncryptor.encrypt(pmsInput.getText()));
    }

    //Przestawienie macierzowe słowo - deszyfrowanie
    @FXML
    public void pmsDecodeClick(){

        if(pmsKey.getText().equals("") || pmsDepth.getText().equals("") || pmsOutput.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Wszystkie pola muszą być wypełnione");

            alert.showAndWait();
            return;
        }
        StringCTEncryptor stringCTEncryptor = new StringCTEncryptor(pmsKey.getText(), Integer.parseInt(pmsDepth.getText()));
        pmsOutput.setText(stringCTEncryptor.decrypt(pmsInput.getText()));
    }

    @FXML
    public void onClickLableRf(){
        toClipboard(rfOutput.getText());
    }
    @FXML
    public void onClickLablePm(){
        toClipboard(pmOutput.getText());
    }
    @FXML
    public void onClickLablePms(){
        toClipboard(pmsOutput.getText());
    }
    public void toClipboard(String clip){
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();
        content.putString(clip);
        clipboard.setContent(content);
    }
}