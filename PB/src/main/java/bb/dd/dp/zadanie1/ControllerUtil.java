package bb.dd.dp.zadanie1;

import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

public class ControllerUtil {
	public static void toClipboard(String clip) {
		final Clipboard clipboard = Clipboard.getSystemClipboard();
		final ClipboardContent content = new ClipboardContent();
		content.putString(clip);
		clipboard.setContent(content);
	}
	
}
