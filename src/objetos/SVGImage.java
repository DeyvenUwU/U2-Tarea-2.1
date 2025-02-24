package objetos;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import javax.swing.JLabel;

public class SVGImage extends JLabel{
    
    private FlatSVGIcon icon;
    
    public void setSVGImage(String imagePath, int width, int height) {
        icon = new FlatSVGIcon(imagePath, width, height);
        this.setIcon(icon);
    }
}
