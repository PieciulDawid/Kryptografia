package bb.dd.dp.zadanie1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


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


}