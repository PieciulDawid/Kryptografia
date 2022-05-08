package bb.dd.dp.zadanie1;

import bb.dd.dp.impl.Encryptor;
import bb.dd.dp.impl3.RSAEncryptor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

public class RsaController {

	@FXML
	void initialize() {
		final Tooltip tooltip = new Tooltip("Click to copy!");
		tooltip.setShowDelay(new Duration(250));
		output.setTooltip(tooltip);
	}
	@FXML
	private TextArea input;
	
	@FXML
	private Label output;
	
	
	private Encryptor encryptor = new RSAEncryptor();
	
	@FXML
	private void encode(ActionEvent e) {
		encryptor = new RSAEncryptor();
		
		final var cipherText = encryptor.encrypt(input.getText());
		
		output.setText(cipherText);
	}
	
	@FXML
	private void decode(ActionEvent e) {
		final var plainText = encryptor.decrypt(input.getText());
		
		output.setText(plainText);
	}
	
	@FXML
	private void copyLabelContents(MouseEvent e) {
		ControllerUtil.toClipboard(output.getText());
	}
	
}
