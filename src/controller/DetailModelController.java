/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Moneymodel;

/**
 *
 * @author chees
 */
public class DetailModelController
{
    MoneyModel selectedModel;
    Scene previousScene;
    
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView image;

    @FXML
    private Button backButton;
    
    @FXML
    private Label labelValue;
    
    @FXML
    private Label labelID;

    @FXML
    void backButtonAction(ActionEvent event) 
    {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //Stage stage = (Stage)backButton.getScene().getWindow();
        if(previousScene !=null)
        {
            stage.setScene(previousScene);
        }
    }

    @FXML
    void initialize() {
        assert image != null : "fx:id=\"image\" was not injected: check your FXML file 'DetailModelView.fxml'.";
        assert labelID != null : "fx:id=\"labelID\" was not injected: check your FXML file 'DetailModelView.fxml'.";
        assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'DetailModelView.fxml'.";
        assert labelValue != null : "fx:id=\"labelValue\" was not injected: check your FXML file 'DetailModelView.fxml'.";
        backButton.setDisable(true);
    }
    public void initData(MoneyModel model)
    {
        selectedModel = model;
        labelID.setText(model.getId().toString());
        labelValue.setText(model.getName());
        try
        {
            String imagename = "/resource/images/" + model.getName() + ".png";
            Image profile = new Image(getClass().getResourceAsStream(imagename));
            image.setImage(profile);
        } 
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

   public void setPreviousScene(Scene scene)
    {
        previousScene = scene;
        backButton.setDisable(false);
    }

    void initData(controller.MoneyModel selectedMoneyModel)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static class MoneyModel
    {

        public MoneyModel()
        {
        }

        private Object getId()
        {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        private String getName()
        {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

}
