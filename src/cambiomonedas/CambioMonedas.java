
package cambiomonedas;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javafx.scene.layout.Border;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class CambioMonedas extends JFrame implements ActionListener{

    JLabel texto1 = new JLabel("Ingreso valores:");
    JLabel texto2 = new JLabel("Matriz:");
    JLabel texto3 = new JLabel("Vueltas:");
    JLabel texto4 = new JLabel("Ingresar numero de monedas: ");
    JLabel texto5 = new JLabel("Ingresar Ingresar valor vueltas: ");
    
    JLabel lPesos;
    
    JTextField fieldMonedas = new JTextField("3");
    JTextField fieldVueltas = new JTextField("12");

    JTextField[] arregloMonedas;
    JTextArea[][] matriz;

    JButton ingresar = new JButton("Ingresar");
    JButton calcular = new JButton("Calcular");

    JPanel jpan1 = new JPanel(); 
    JPanel jpan2 = new JPanel();
    JPanel jpan3 = new JPanel();
    
    int valorOptimo;
    
    int columA,columB,filasF,columF;

    int contador;
    
    int monedas, vueltas;
    
    public static void main(String[] args) {
        
        CambioMonedas moneda = new CambioMonedas();
        moneda.setSize(1200, 710);
        moneda.setTitle("Problema de la mochila");
        moneda.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        moneda.setVisible(true); 
        
    }

    CambioMonedas(){
        
         Container c = getContentPane();
        c.setLayout(null);
        this.getContentPane().setBackground(Color.LIGHT_GRAY);
        
        c.add(jpan1);
        c.add(jpan2);
        c.add(jpan3);
        
        c.add(texto1);
        c.add(texto2);
        c.add(texto3);
        c.add(texto4);
        c.add(texto5);
        
        c.add(fieldMonedas);
        c.add(fieldVueltas);
        
        c.add(ingresar);
        c.add(calcular);
        
        texto1.setBounds(10, 100, 200, 20);
        texto2.setBounds(10, 200, 200, 20);
        texto3.setBounds(350, 100, 200, 20);
        texto4.setBounds(10, 20, 250, 20);
        texto5.setBounds(10, 50, 250, 20);
        
        fieldMonedas.setBounds(250, 20, 20, 20);
        fieldVueltas.setBounds(250, 50, 20, 20);
        
        ingresar.addActionListener(this);
        ingresar.setBounds(330, 20, 100, 20);
        calcular.addActionListener(this);
        calcular.setBounds(330, 50, 100, 20);
     
        jpan1.setBounds(10, 120, 250, 70);
        jpan1.setBackground(Color.WHITE);
        jpan2.setBounds(10, 220, 1150, 400);
        jpan2.setBackground(Color.DARK_GRAY);
        jpan3.setBounds(350, 120, 220, 70);
        jpan3.setBackground(Color.WHITE);
        
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == ingresar){
        
            jpan1.removeAll();
            
            monedas = Integer.parseInt(fieldMonedas.getText());
            vueltas = Integer.parseInt(fieldVueltas.getText());
            
            arregloMonedas = new JTextField [monedas];
            
            lPesos = new JLabel("Digite pesos de las monedas:");
            lPesos.setBounds(0,0,200,20);
            
            jpan1.add(lPesos);
            
            jpan1.repaint();
            
            for(int i = 0; i<monedas;i++){
                 
                        arregloMonedas[i] = new JTextField(Integer.toString(i));
                        arregloMonedas[i].setBounds(i*30,30,20,20);
                        jpan1.add(arregloMonedas[i]);
                    
                }
            
        } else if(e.getSource() == calcular) {
            
            for(int i=0;i < monedas - 1; i++) {
            
                for(int j=i+1; j < monedas; j++) {

                    if(Integer.parseInt(arregloMonedas[i].getText()) > Integer.parseInt(arregloMonedas[j].getText())) {

                            String temp = arregloMonedas[i].getText();
                            arregloMonedas[i].setText(arregloMonedas[j].getText());
                            arregloMonedas[j].setText(temp);
                            
                        }

                }
            
            }
            
            jpan2.removeAll();
            
            matriz = new JTextArea[monedas + 2][vueltas + 2];
            
            for(int i = 0; i<monedas + 2 ;i++){
                
                for(int j = 0; j < vueltas + 2 ;j++){
                   
                    if(i == 0 && j == 0){
                            
                        matriz[i][j] = new JTextArea("Col. \n Valores");
                        
                    } else if( i == 0 && j>0){
                        
                        matriz[i][j] = new JTextArea(Integer.toString(j-1));
                        
                    } else if(i == 1 && j>0){
                    
                        matriz[i][j] = new JTextArea(Integer.toString(999));
                        
                    }else if(i > 1 && j == 0){
                    
                        matriz[i][j] = new JTextArea(arregloMonedas[i-2].getText());
                        
                    } else {
                    
                        if( (i == 2) && (j < Integer.parseInt(arregloMonedas[i-2].getText())) ){
                            
                            
                            
                        }else{
                            
                            matriz[i][j] = new JTextArea(Integer.toString(0));
                            
                        }
                        
                        
                        
                        
                    }
                    
                    
                    matriz[i][j].setBounds(j*60 + 5 ,i*70 + 5,50,60);
                    jpan2.add(matriz[i][j]);
                    
                }
                
            }
            
            jpan2.repaint();
            
        }
        
    }
    
    int hallarMin( int a, int b){
        
        if(a<b){
            
            return a; 
            
        } else {
            
            return b;
            
        }
        
    }
    
}
