/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.Moneymodel;

/**
 *
 * @author chees
 */
public class FXMLDocumentController implements Initializable
{

    @FXML
    private TableColumn<MoneyModel, Double> MoneyModelSpent;

    @FXML
    private Button buttonSpent;

    @FXML
    private TextField textboxName;

    @FXML
    private Label label;

    @FXML
    private Button button;
    
    @FXML
    private Button buttonReadByName;

    @FXML
    private Button buttonRead;

    @FXML
    private TableColumn<MoneyModel, Double> MoneyModelAdded;

    @FXML
    private AnchorPane MoneyModelTabel;

    @FXML
    private Button deleteMoneyModel;

    @FXML
    private Button buttonReadByIDAndName;

    @FXML
    private Button buttonReadByNameAndAdded;

    @FXML
    private TableColumn<MoneyModel, Integer> MoneyModelID;
    
    @FXML
    private TableColumn<MoneyModel, String> MoneyModelName;

    @FXML
    private Button buttonUpdate;

    @FXML
    private Button buttonAdd;

    @FXML
    private TableView<MoneyModel> MoneyModelTable;

    private ObservableList<MoneyModel> moneymodelData;

    public void setTableData(List<MoneyModel> moneymodelList)
    {
        moneymodelData = FXCollections.observableArrayList();
        moneymodelList.forEach(m ->
        {
            moneymodelData.add(m);
        });
        MoneyModelTable.setItems(moneymodelData);
        MoneyModelTable.refresh();
    }

    @FXML
    void addMoneyModel(ActionEvent event)
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter id: ");
        int id = input.nextInt();
        System.out.println("Enter money added: ");
        double added = input.nextDouble();


        Moneymodel moneymodel = new Moneymodel();

        moneymodel.setId(id);
        moneymodel.setAdded(added);

        add(moneymodel);
    }

    @FXML
    void spentMoneyModel(ActionEvent event)
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter id: ");
        int id = input.nextInt();
        System.out.println("Enter money spent: ");
        double spent = input.nextDouble();


        Moneymodel moneymodel = new Moneymodel();

        moneymodel.setId(id);
        moneymodel.setSpent(spent);

        spent(moneymodel);
    }

    @FXML
    void updateMoneyModel(ActionEvent event)
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter id: ");
        int id = input.nextInt();
        System.out.println("Enter name: ");
        String name = input.next();
        System.out.println("Enter money added: ");
        double added = input.nextDouble();
        System.out.println("Enter money spent: ");
        double spent = input.nextDouble();

        Moneymodel moneymodel = new Moneymodel();

        moneymodel.setId(id);
        moneymodel.setName(name);
        moneymodel.setAdded(added);
        moneymodel.setSpent(spent);
        update(moneymodel);
    }

    @FXML
    void readMoneyModel(ActionEvent event)
    {
        Scanner input = new Scanner(System.in);
        int id = input.nextInt();
        MoneyModel m = readMoneyModel(id);
        System.out.println(m.toString());
    }
    @FXML
    void readByName(ActionEvent event)
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter name: ");
        String name = input.next();
        List<MoneyModel> m = readByName(name);
        System.out.println(m.toString());
    }
    @FXML
    void deleteMoneyModel(ActionEvent event)
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter id: ");
        int id = input.nextInt();
        MoneyModel m = readMoneyModel(id);
        System.out.println("deleting: " + m.toString());
        delete(m);
    }

    @FXML
    void readByNameAndAdded(ActionEvent event)
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter name: ");
        String name = input.next();
        System.out.println("Enter money added: ");
        double added = input.nextDouble();

        List<MoneyModel> m = readByNameAndAdded(name, added);
    }

    @FXML
    void readByIDAndName(ActionEvent event)
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter id: ");
        int id = input.nextInt();
        System.out.println("Enter name: ");
        String name = input.next();

        List<MoneyModel> m = readByIDAndName(id, name);
    }

    @FXML
    void searchByNameAction(ActionEvent event)
    {
        System.out.println("Clicked");
        String name = textboxName.getText();
        List<MoneyModel> moneymodels = readByName(name);
        if(moneymodels == null || moneymodels.isEmpty())
        {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog Box");
            alert.setHeaderText("This is header section to write heading");
            alert.setContentText("No moneymodel");
            alert.showAndWait();
        }
        else
        {
            setTableData(moneymodels);
        }
    }
     @FXML
    void searchByNameAdvancedAction(ActionEvent event)
    {
        System.out.println("Clicked");
        String name = textboxName.getText();
        List<MoneyModel> moneymodels = readByNameAdvanced(name);
        if(moneymodels == null || moneymodels.isEmpty())
        {
             Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog Box");
            alert.setHeaderText("This is header section to write heading");
            alert.setContentText("No moneymodel");
            alert.showAndWait();
        }
        else
        {
            setTableData(moneymodels);
        }
    }

    @FXML
    void actionShowDetailsInPlace(ActionEvent event) throws IOException 
    {
        System.out.println("Clicked");
        MoneyModel selectedMoneyModel = MoneyModelTable.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DetailModelView/fxml"));
        Parent detailModelView =loader.load();
        Scene tableViewScene = new Scene(detailModelView);
        DetailModelController detailControlled = loader.getController();
        detailControlled.initData(selectedMoneyModel);
        Scene currentScene = ((Node) event.getSource()).getScene();
        detailControlled.setPreviousScene(currentScene);
        Stage stage = (Stage) currentScene.getWindow();
        stage.setScene(tableViewScene);
        stage.show(); 
    }

    @FXML
    void actionShowDetails(ActionEvent event) throws IOException
    {
        System.out.println("Clicked");
        MoneyModel selectedMoneyModel = MoneyModelTable.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DetailModelView/fxml"));
        Parent detailModelView =loader.load();
        Scene tableViewScene = new Scene(detailModelView);
        DetailModelController detailControlled = loader.getController();
        detailControlled.initData(selectedMoneyModel);
        Stage stage = new Stage();
        stage.setScene(tableViewScene);
        stage.show();
    }
            
    @FXML
    private void handleButtonAction(ActionEvent event)
    {
        System.out.println("You clicked me!");
        label.setText("Hello World!");

        Query query = manager.createNamedQuery("Moneymodel.findAll");
        List<Moneymodel> data = query.getResultList();
        for (Moneymodel m : data)
        {
            System.out.println(m.getId() + " " + m.getAdded() + " " + m.getSpent() +
            " " + m.getName());
            //code from demo
        }
    }
    EntityManager manager;  // code from demo

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        manager = (EntityManager) Persistence.createEntityManagerFactory("MaddieSchmittFXMLPU").createEntityManager();
        MoneyModelName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        MoneyModelID.setCellValueFactory(new PropertyValueFactory<>("Id"));
        MoneyModelAdded.setCellValueFactory(new PropertyValueFactory<>("Added"));
        MoneyModelSpent.setCellValueFactory(new PropertyValueFactory<>("Spent"));
        MoneyModelTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

    }

    public void add(Moneymodel moneymodel)
    {
        try
        {
            manager.getTransaction().begin();
            if (moneymodel.getId() != null)
            {
                manager.persist(moneymodel);
                manager.getTransaction().commit();
                System.out.println(moneymodel.toString() + " is added");
            }
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
     private void spent(Moneymodel moneymodel)
    {
        try
        {
            manager.getTransaction().begin();
            if (moneymodel.getId() != null)
            {
                manager.persist(moneymodel);
                manager.getTransaction().commit();
                System.out.println(moneymodel.toString() + " is spent");
            }
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    private MoneyModel readMoneyModel(int id)
    {
        Query query = manager.createNamedQuery("MoneyModel.findByID");
        query.setParameter("id", id);
        MoneyModel moneymodel = (MoneyModel) query.getSingleResult();
        if (moneymodel != null)
        {
            System.out.println(moneymodel.getId() + " " + moneymodel.getAdded() + " "
                    + moneymodel.getSpent() + " " + moneymodel.getName());
        }
        return moneymodel;
    }

    private void update(Moneymodel model)
    {
        try
        {
            MoneyModel exisitingMoneyModel = manager.find(MoneyModel.class, model.getId());
            if (exisitingMoneyModel != null)
            {
                manager.getTransaction().begin();
                exisitingMoneyModel.setAdded(model.getAdded());
                exisitingMoneyModel.setSpent(model.getSpent());
                exisitingMoneyModel.setName(model.getName());
                manager.getTransaction().commit();
            }
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    private void delete(MoneyModel m)
    {
        try
        {
            MoneyModel exisitingMoneyModel = manager.find(MoneyModel.class, m.getId());
            if (exisitingMoneyModel != null)
            {
                manager.getTransaction().begin();
                manager.remove(exisitingMoneyModel);
                manager.getTransaction().commit();
            }
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public List<MoneyModel> readByIDAndName(int id, String name)
    {
        Query query = manager.createNamedQuery("MoneyModel.findByIdAndName");
        query.setParameter("id", id);
        query.setParameter("name", name);

        List<MoneyModel> m = query.getResultList();
        for (MoneyModel moneymodel : m)
        {
            System.out.println(moneymodel.getId() + " " + moneymodel.getSpent() + " " + moneymodel.getAdded() +
                    " " + moneymodel.getName());
        }
        return m;
    }

    private List<MoneyModel> readByNameAndAdded(String name, double added)
    {
        Query query = manager.createNamedQuery("MoneyModel.findByNameAndAdded");
        query.setParameter("name", name);
        query.setParameter("added", added);

        List<MoneyModel> m = query.getResultList();
        for (MoneyModel moneymodel : m)
        {
            System.out.println(moneymodel.getId() + " " + moneymodel.getAdded() + " " + moneymodel.getSpent() + 
                    " " + moneymodel.getName());
        }
        return m;
    }

    private List<MoneyModel> readByName(String name)
    {
        Query query = manager.createNamedQuery("MoneyModel.findByName");
        query.setParameter("name", name);

        List<MoneyModel> m = query.getResultList();
        for (MoneyModel moneymodel : m)
        {
            System.out.println(moneymodel.getId() + " " + moneymodel.getAdded() + " " + moneymodel.getSpent() + 
                    " " + moneymodel.getName());
        }
        return m;
    }

    private List<MoneyModel> readByNameAdvanced(String name)
    {
        Query query = manager.createNamedQuery("MoneyModel.findByNameAdvanced");
        query.setParameter("name", name);

        List<MoneyModel> m = query.getResultList();
        for (MoneyModel moneymodel : m)
        {
            System.out.println(moneymodel.getId() + " " + moneymodel.getAdded() + " " + moneymodel.getSpent() + 
                   " " + moneymodel.getName());
        }
        return m;
    }


}
