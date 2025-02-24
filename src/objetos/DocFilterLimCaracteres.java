package objetos;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class DocFilterLimCaracteres extends DocumentFilter {
    private static final int MAX_CARACTERES = 45;

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        if (fb.getDocument().getLength() + string.length() <= MAX_CARACTERES) {
            super.insertString(fb, offset, string, attr);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if (fb.getDocument().getLength() + text.length() - length <= MAX_CARACTERES) {
            super.replace(fb, offset, length, text, attrs);
        }
    }
}
