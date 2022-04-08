package bb.dd.dp.zadanie1;

import bb.dd.dp.impl.Encryptor;
import bb.dd.dp.impl3.RSAEncryptor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

public class RsaController {
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
