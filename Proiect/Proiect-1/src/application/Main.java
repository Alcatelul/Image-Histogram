package application;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javafx.geometry.Insets; 
import javafx.scene.Scene; 
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.scene.control.TextField; 
import javafx.stage.Stage;  
import javafx.stage.FileChooser;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.layout.*; 
import javafx.scene.control.*; 
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ToolBar;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ToggleButton;



public class Main extends Application {
	Scene scene1,scene2; 
	
	@SuppressWarnings({ "unused", "unchecked" })
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("Aplicatie IGU-Histograma Imagine");
			GridPane root1 = new GridPane();
			Label label1 = new Label("Aplicatie pentru histograma unei imagini de tip bmp");
			Label label2 = new Label("Cautati imaginea");
			ProgressBar p_bar1 = new ProgressBar(); 
			ToolBar toolBarLink = new ToolBar();
			p_bar1.setProgress(0.0f); 
			CheckBox check1 = new CheckBox("Apasati pentru scris mare la titlu!");  
			Button buton_alegere_imagine = new Button("Cautare");
			check1.setOnAction(e->{
				if(check1.isSelected())
					label1.setFont(Font.font("Cambria", 15));
				else
					label1.setFont(Font.font("Cambria", 12));
			});
			 
			TabPane Tab_pane = new TabPane();
			
			for (int i = 0; i < 3; i++) { 
				  GridPane root2 = new GridPane();
	            // create Tab 
	            Tab tab = new Tab("Tab " + (int)(i + 1)); 
	            VBox vbox = new VBox();
	            // create a label 
	            Label label5 = new Label("Adaugati un comentariu avansat:");
	            TextField text_field1=new TextField();  
	            Button button_text_field1 = new Button("Adauga comentariul");
	            
	            button_text_field1.setOnAction(e->{
	            	 // Image Source
	            	 FileInputStream input = null;
					try {
						input = new FileInputStream("C:\\Users\\Alin-PC\\Desktop\\Imagine\\Minimar.PNG");
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}  
	                Image image = new Image(input);
	                ImageView imageView = new ImageView(image);
	                
	                //Advanced Labels
	                // ------ Label 1 -----
	                Label label11 = new Label(text_field1.getText());
	          
	                // Set Image
	                label11.setGraphic(imageView);
	          
	                // ------ Label 2 -----
	                Label label22 = new Label(text_field1.getText());
	          
	                label22.setFont(Font.font("Cambria", 32));
	                label22.setTextFill(Color.web("#0076a3"));
	          
	                // ------ Label 3 -----
	                Label label33 = new Label(text_field1.getText());
	                label33.setMaxWidth(100);
	          
	                label33.setWrapText(true);
	          
	                // ------ Label 4 -----
	                Label label44 = new Label(text_field1.getText());
	          
	                // Rotate 45 degrees
	                label44.setRotate(45);
	          
	                // Translate Y axis 30 pixel
	                label44.setTranslateY(30);
	                FlowPane root = new FlowPane();
	                root.setPadding(new Insets(10));
	                root.setHgap(10);
	                root.setVgap(10);
	                root.getChildren().addAll(label11, label22, label33, label44);
	                root2.add(root, 7, 5);
	                });
	       
	            // add label to the tab 
	            if(i==0) {
	            	root2.add( button_text_field1, 0, 5);
	            	root2.add(p_bar1, 2, 3);
	            	root2.add(text_field1, 1, 4);
	            	root2.add(label5, 0, 4);
	            	root2.add(label1,0,2);
	    			root2.add(label2,0,3);
	    			root2.add(buton_alegere_imagine,1,3);
	    			root2.add(check1,0,6);
	    			 tab.setContent(root2); 
	            }
	            if(i==1) {
	            	
	            	
            	     String[] captions = new String[]{
            		 "Link Produse Oracle",
            		 "Link tutorialspoint JavaFX",
            		 "Link javatpoint JavaFX"
            		       
            		    };
	            		 
        		    String[] urls = new String[]{
        		        "https://www.oracle.com/products/",
        		    	"https://www.tutorialspoint.com/javafx/index.htm",
        		    	"https://www.javatpoint.com/javafx-tutorial"
        		    		
        		        
        		    };
	            		   
        		    Hyperlink[] hpls = new Hyperlink[captions.length];
        		
        		   
        	        
        	         WebView browser = new WebView();
        	         WebEngine webEngine = browser.getEngine();
        	 
        	        for (int j = 0; j < captions.length; j++) {
        	            Hyperlink hpl = hpls[j] = new Hyperlink(captions[j]);
      
        	            hpl.setFont(Font.font("Arial", 14));
        	             String url = urls[j];
        	 
        	            hpl.setOnAction(e-> {
        	                    webEngine.load(url); 
        	            });
        	        }
        	       
        	        toolBarLink.getItems().add(hpls[0]);
        	        toolBarLink.getItems().add(new Separator());
        	        toolBarLink.getItems().add(hpls[1]);
        	        toolBarLink.getItems().add(new Separator());
        	        toolBarLink.getItems().add(hpls[2]);
        	        toolBarLink.getItems().add(new Separator());
        	        HBox hbox = new HBox();
        	        hbox.getChildren().add(toolBarLink);
	                vbox.getChildren().addAll(hbox, browser);
	                VBox.setVgrow(browser, Priority.ALWAYS);
	                tab.setContent(vbox); 
	            }
	            
	            if(i==2) {
	            	@SuppressWarnings("rawtypes")
					ChoiceBox choiceBox = new ChoiceBox();
	            	choiceBox.getItems().add("Albastru");
	            	choiceBox.getItems().add("Rosu");
	            	choiceBox.getItems().add("Galben");
	            	choiceBox.setOnAction(e->{
	            		String value=(String) choiceBox.getValue();
	            		if(value=="Albastru")
	            			root2.setStyle("-fx-background-color: blue");
	            		if(value=="Rosu")
	            			root2.setStyle("-fx-background-color: red");
	            		if(value=="Galben")
	            			root2.setStyle("-fx-background-color: yellow");
	            	});
	            	
	            	root2.add( choiceBox, 0, 1);
	            	 tab.setContent(root2);
	            }
	            
	            
	            
	            // add tab 
	            Tab_pane.getTabs().add(tab); 
	        } 
			
			 
			root1.add(Tab_pane, 0, 0);
			FileChooser fileChooser = new FileChooser();
			ToggleButton toggleButton1 = new ToggleButton("Afisare img");
			 root1.add(toggleButton1,0,7);
			 
			 buton_alegere_imagine.setOnAction(e -> {
				 int index=-1;
			 	//fileChooser.setInitialDirectory(new File("C:\\Users\\Alin-PC\\Desktop\\Imagine"));
	            File selectedFile = fileChooser.showOpenDialog(primaryStage);
	            //verificare ca fisierul citit sa fie bmp
	            if (selectedFile.getName().toLowerCase().endsWith("bmp"))
                  { //Citire informatii importante (height,etc)
	            	
	            	FileInputStream input2 = null;
	            	 ImageView imageView2 =null;
	            	
	            	 
	            	if(toggleButton1.isSelected()) {
	            		try { System.out.println("Pas img:");
							input2= new FileInputStream(selectedFile.getAbsolutePath().toLowerCase());
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						}
	       			 Image image2 = new Image(input2);
	                 imageView2 = new ImageView(image2);
	                 root1.add(imageView2,0,5);
	                index = root1.getChildren().indexOf(imageView2);
	                 System.out.println(index);
	                    
	            	}
	            	else
	            		if(imageView2!=null && !toggleButton1.isSelected())
	            	{
	            	
	            	}
	     
	            	
	            	
		            	Init_citire Init=new Init_citire();
	                  System.out.println(selectedFile.getAbsolutePath());
	                  Init.f_Init_citire(selectedFile.getAbsolutePath().toLowerCase());
	                  
	                //Citire date din imagine pentru histograma
	                  long startTime = System.currentTimeMillis();
	          	    Citire_date_histograma Date_hist=new Citire_date_histograma();
	          	    int []date=new int[257];
	          	    int[] x=Date_hist.f_Citire_date_histograma(Init.width,Init.height,Init.paddingBytes,selectedFile.getAbsolutePath().toLowerCase());
	          	    for(int i=0;i<256;i++)
	          			{date[i]=x[i];
	          			}
	          	    long stopTime = System.currentTimeMillis();
	          	    long elapsedTime = stopTime - startTime;
	          	    System.out.println("Timp pentru preluarea datelor histogramei:"+elapsedTime+" ms");
	                  
	          	//Creare histograma
	        	    startTime = System.currentTimeMillis();
	        	    for(int i=0;i<256;i++)
	        			{Creare_Salvare_Histograma.data[i]=x[i];
	        			}
	        	    JFrame f = new JFrame();
	        	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        	    f.add(new Creare_Salvare_Histograma());
	        	    f.setSize(1200,650);
	        	    f.setLocation(50,50);
	        	    f.setVisible(true);
	        	    
	        	    stopTime = System.currentTimeMillis();
	        	 	elapsedTime = stopTime - startTime;
	        	 	System.out.println("Timp realizare obiect grafic:"+elapsedTime+" ms");
	                 
	        	 	//Afisare imagine in a doua fereastra
	        	 
	        	 	 Image image = SwingFXUtils.toFXImage(Creare_Salvare_Histograma.getScreenShot(f), null);
	        	 	ImageView imageView = new ImageView(image);
	  
	        	 	 Stage stage = new Stage();
	        	 	 Button saveBtn = new Button("Salvare histograma");
	        	 	 f.dispose();
	        	 	 
	        	 	p_bar1.setProgress(100.0f);  
	        	 	saveBtn.setOnAction(e2 -> {
	        	        
	        	            FileChooser fileChooser1 = new FileChooser();
	        	            fileChooser1.setTitle("Salveaza imagine");
	        	            //fileChooser1.setInitialDirectory(new File("C:\\Users\\Alin-PC\\Desktop\\Imagine"));
	        	            fileChooser1.setInitialFileName("Histograma.png");
	        	            fileChooser1.getExtensionFilters().addAll(
	        	            	     new FileChooser.ExtensionFilter("PNG Files", "*.png")
	        	            	);
	        	            File file = fileChooser1.showSaveDialog(stage);
	        	            if (file != null) {
	        	                try {
	        	                    ImageIO.write(SwingFXUtils.fromFXImage(imageView.getImage(),
	        	                        null), "png", file);
	        	                    p_bar1.setProgress(0.0f); 
	        	                    stage.close();
	        	                } catch (IOException ex) {
	        	                    System.out.println("Nu se salveaza!");
	        	                }
	        	            }
	        	        
	        	    }
	        	);
	        	 	 
		      GridPane root2 = new GridPane();  
		                  
	          Label label4 = new Label("Acesta este scena 2"); 
	          root2.add(label4,0,1);
	          root2.add(imageView,0,2);
	          root2.add(saveBtn,0,3);
	          
	         
	          scene2= new Scene(root2,1300,700);
	          stage.setScene(scene2);
	          stage.show();
	          }
	        //daca fisierul citit nu este bmp
	        else
	        {
	        	Label label_not_bmp = new Label("Eroare:cititi alt fisier,acesta nu este de tip bmp!"); 
	        	root1.add(label_not_bmp,0,0);
	        }
			});
			Scene scene = new Scene(root1,700,400);
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
