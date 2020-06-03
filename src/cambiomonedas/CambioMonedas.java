
package cambiomonedas;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
    JPanel jpan3 = new JPanel();
    
    JScrollPane scrollPane = new JScrollPane();
    JScrollPane scrollPane1 = new JScrollPane();
    
    int monedas, vueltas;
    
    javax.swing.border.Border border = BorderFactory.createLineBorder(Color.BLACK);
    
    public static void main(String[] args) {
        
        CambioMonedas moneda = new CambioMonedas();
        moneda.setSize(1200, 710);
        moneda.setTitle("Cambio Monedas");
        moneda.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        moneda.setVisible(true); 
        
    }

    CambioMonedas(){
        
         Container c = getContentPane();
        c.setLayout(null);
        this.getContentPane().setBackground(Color.LIGHT_GRAY);
        
        c.add(jpan1);
        c.add(jpan3);
        
        c.add(scrollPane1);
        
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
        jpan3.setBounds(350, 120, 220, 70);
        jpan3.setBackground(Color.WHITE);
        
        scrollPane.setBounds(10, 220, 2000, 2000);
        scrollPane.setPreferredSize(new Dimension(2000, 2000));  
        
        scrollPane1.setBounds(10, 220, 1150, 400);
        scrollPane1.setPreferredSize(new Dimension(1150, 400)); 
        
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
            
            int x=5;
            
            for(int i = 0; i<monedas;i++){
                 
                    arregloMonedas[i] = new JTextField(Integer.toString(x));
                    arregloMonedas[i].setBounds(i*30,30,20,20);
                    jpan1.add(arregloMonedas[i]);
                    x--;
                        
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
            
            scrollPane.removeAll();
            
            matriz = new JTextArea[monedas + 2][vueltas + 2];
            
            for(int i = 0; i<monedas + 2 ;i++){
                
                for(int j = 0; j < vueltas + 2 ;j++){
                   
                    if(i == 0 && j == 0){
                            
                        matriz[i][j] = new JTextArea("Col. \n Valores");
                        
                    } else if( i == 0 && j>0){
                        
                        matriz[i][j] = new JTextArea(Integer.toString(j-1));
                        
                    } else if(i == 1 && j>0){
                    
                        matriz[i][j] = new JTextArea(Double.toString(Double.POSITIVE_INFINITY));
                        
                    }else if(i > 1 && j == 0){
                    
                        matriz[i][j] = new JTextArea(arregloMonedas[i-2].getText());
                        
                    } else {
                    
                        int valor = 0;
                        
                        if(i > 1){
                        
                            valor = Integer.parseInt(arregloMonedas[i-2].getText());
                        
                        }
                        
                        if(j==1 && i>1){
                            
                            matriz[i][j] = new JTextArea(Integer.toString(0));
                            
                        } else if( (i == 2) && (j <= valor) ){
                            
                            matriz[i][j] = new JTextArea( Double.toString(Double.POSITIVE_INFINITY) );
                            
                        } else if( (i == 2) && (j >= valor)){
                            
                            String cadena = matriz[i][j - valor ].getText();
                            
                            
                            
                            double cantidad = 1 + Double.parseDouble(cadena);
                            
                            if(cantidad != Double.POSITIVE_INFINITY){
                                
                                matriz[i][j] = new JTextArea(Double.toString(cantidad));
                            
                                
                            } else {
                                
                                matriz[i][j] = new JTextArea(Double.toString(cantidad));
                                 
                            }
                            
                        } else if( j < valor){
                            
                            String cadena = matriz[i-1][j].getText();
                            
                            
                            double cantidad = Double.parseDouble(cadena);
                            
                            if(cantidad != Double.POSITIVE_INFINITY){
                                
                                matriz[i][j] = new JTextArea(Double.toString(cantidad));
                            
                                
                            } else {
                                
                                matriz[i][j] = new JTextArea(Double.toString(cantidad));
                                 
                            }
                            
                            
                        }else if(i>2){
                        
                            
                            double a = Double.parseDouble(matriz[i-1][j].getText());
                            double b = 1 + Double.parseDouble(matriz[i][j - valor].getText());
                            
                            if(a<=b){

                                matriz[i][j] = new JTextArea(Double.toString(a));
                                
                            } else {

                                matriz[i][j] = new JTextArea(Double.toString(b));

                            }
//                            

                        }else {           
                           
                            matriz[i][j] = new JTextArea("0");
                            
                        }
                        
                        
                        
                        
                    }
                    
                    matriz[i][j].setBorder(border);
                    matriz[i][j].setBounds(j*60 + 5 ,i*110 + 5,50,90);
                    scrollPane.add(matriz[i][j]);
                    
                }
                
            }
            
            scrollPane.repaint();
            scrollPane1.setViewportView(scrollPane);
            
        }
        
    }
    
    double hallarMin( double a, double b){
        
        if(a<b){
            
            return a; 
            
        } else {
            
            return b;
            
        }
        
    }
    
}
