import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PhotoFrame extends javax.swing.JFrame {
    private final JLabel jlbphotos = new JLabel("photos");
    private JComboBox<String> jComboxPhotos;
    private final JButton jbtnTraiter = new JButton("Traiter");
    private final PannelPhoto pannelPhoto = new PannelPhoto();


    public PhotoFrame() {
        try {

            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            this.setLayout(new BorderLayout());
            File f = new File("C:\\Users\\hoang\\OneDrive\\Bureau\\Cours\\2èmeAnnée\\Balbali\\TP7\\untitled\\src\\photos\\");

            String[] images = f.list();
            jComboxPhotos = new JComboBox<>(images);
            String nomPhoto = (String) jComboxPhotos.getSelectedItem();
            // maintenant, j'ai besoin de lire le contenu de la photo

            BufferedImage bi = ImageIO.read(new File("C:\\Users\\hoang\\OneDrive\\Bureau\\Cours\\2èmeAnnée\\Balbali\\TP7\\untitled\\src\\photos\\" + nomPhoto));

            //lorsque nous utilisons read il faut gérer les exceptions avec un try catch

            pannelPhoto.setBi(bi);
            JPanel jPanelN = new JPanel();
            jPanelN.setLayout(new FlowLayout());
            jPanelN.add(jlbphotos);
            jPanelN.add(jComboxPhotos);
            jPanelN.add(jbtnTraiter);
            this.setBounds(10, 10, 800, 600);
            this.add(jPanelN, BorderLayout.NORTH);
            this.add(pannelPhoto, BorderLayout.CENTER);
            this.setVisible(true);

            //gestion des évènements
            jComboxPhotos.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        String nomPhoto = (String) jComboxPhotos.getSelectedItem();

                        // maintenant, j'ai besoin de lire le contenu de la photo

                        BufferedImage bi = ImageIO.read(new File("C:\\Users\\hoang\\OneDrive\\Bureau\\Cours\\2èmeAnnée\\Balbali\\TP7\\untitled\\src\\photos\\" + nomPhoto));
                        //lorsque nous utilisons read il faut gérer les exceptions avec un try catch

                        pannelPhoto.setBi(bi);
                        pannelPhoto.repaint();// cette méthode va executer de nouveau paintComponent

                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
            });


            jbtnTraiter.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    BufferedImage bi = pannelPhoto.getBi();
                    for (int i = 0; i < bi.getWidth(); i++) //on parcours les colonnes

                        for (int j = 0; j < bi.getHeight(); j++) {
                            //on parcous les lignes
                            bi.setRGB(i, j, (bi.getRGB(i, j) * 5));//on va modifier les pixels
                        }
                    pannelPhoto.repaint();//c'est à dire, on demande à redessiner
                }
            });

        } catch (IOException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        new PhotoFrame();
    }
}