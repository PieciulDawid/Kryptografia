package bb.dd.dp.zadanie1;

import bb.dd.dp.impl.NumericCTEncryptor;
import bb.dd.dp.impl.RailFenceEncryptor;
import bb.dd.dp.impl.StringCTEncryptor;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.Arrays;


// rf - Rail fence
// pm - Przestawienie macierzowe
// pms - Przestawienie macierzowe słowo
public class HelloController {

    @FXML
    void initialize() {}


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
        RailFenceEncryptor railFenceEncryptor = new RailFenceEncryptor(Integer.parseInt(rfKey.getText()));
        rfOutput.setText(railFenceEncryptor.encrypt(rfInput.getText()));
    }

    //Rail fence - deszyfrowanie
    @FXML
    public void rfDecodeClick(){
        RailFenceEncryptor railFenceEncryptor = new RailFenceEncryptor(Integer.parseInt(rfKey.getText()));
        rfOutput.setText(railFenceEncryptor.decrypt(rfInput.getText()));
    }

    //Przestawienie macierzowe - szyfrowanie
    @FXML
    public void pmEncodeClick(){
        String pmKeyString = pmKey.getText();
        int[] key = Arrays.stream(pmKeyString.split(",")).mapToInt(Integer::parseInt).toArray();
        NumericCTEncryptor numericCTEncryptor = new NumericCTEncryptor(key,Integer.parseInt(pmDepth.getText()));
        pmOutput.setText(numericCTEncryptor.encrypt(pmInput.getText()));
    }

    //Przestawienie macierzowe - deszyfrowanie
    @FXML
    public void pmDecodeClick(){
        String pmKeyString = pmKey.getText();
        int[] key = Arrays.stream(pmKeyString.split(",")).mapToInt(Integer::parseInt).toArray();
        NumericCTEncryptor numericCTEncryptor = new NumericCTEncryptor(key,Integer.parseInt(pmDepth.getText()));
        pmOutput.setText(numericCTEncryptor.decrypt(pmInput.getText()));
    }

    //Przestawienie macierzowe słowo - szyfrowanie
    @FXML
    public void pmsEncodeClick(){
        StringCTEncryptor stringCTEncryptor = new StringCTEncryptor(pmsKey.getText(), Integer.parseInt(pmsDepth.getText()));
        pmsOutput.setText(stringCTEncryptor.encrypt(pmsInput.getText()));
    }

    //Przestawienie macierzowe słowo - deszyfrowanie
    @FXML
    public void pmsDecodeClick(){
        StringCTEncryptor stringCTEncryptor = new StringCTEncryptor(pmsKey.getText(), Integer.parseInt(pmsDepth.getText()));
        pmsOutput.setText(stringCTEncryptor.decrypt(pmsInput.getText()));
    }
}