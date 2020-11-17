/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.Persistence;
import javax.persistence.Query;
import static javax.xml.ws.Endpoint.create;
import model.Moneymodel;

/**
 *
 * @author chees
 */
public class FXMLDocumentController implements Initializable
{

    @FXML
    private Label label;
    @FXML
    private Button buttonCreate;

    @FXML
    private Button buttonRead;

    @FXML
    private Button buttonDelete;

    @FXML
    private Button buttonUpdate;
    
    @FXML
    private Button buttonFindByIdAndSpent;
    
    @FXML
    private Button buttonFindByIdAndAdded;

    @FXML
    void updateMoneymodel(ActionEvent event)
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter id: ");
        int id = input.nextInt();
        System.out.println("Enter money added: ");
        double added = input.nextDouble();
        System.out.println("Enter money spent: ");
        double spent = input.nextDouble();
        
        Moneymodel moneymodel = new Moneymodel();
        
        moneymodel.setId(id);
        moneymodel.setAdded(added);
        moneymodel.setSpent(spent);
        update(moneymodel);
    }

    @FXML
    void createMoneymodel(ActionEvent event)
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter id: ");
        int id = input.nextInt();
        System.out.println("Enter money added: ");
        double added = input.nextDouble();
        System.out.println("Enter money spent: ");
        double spent = input.nextDouble();
        
        Moneymodel moneymodel = new Moneymodel();
        
        moneymodel.setId(id);
        moneymodel.setAdded(added);
        moneymodel.setSpent(spent);
        create(moneymodel);

    }

    @FXML
    void readMoneymodel(ActionEvent event)
    {
        Scanner input = new Scanner(System.in);
        int id = input.nextInt();
        MoneyModel m = readMoneyModel(id);
        System.out.println(m.toString());
    }

    @FXML
    void deleteMoneymodel(ActionEvent event)
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter id: ");
        int id = input.nextInt();
        MoneyModel m = readMoneyModel(id);
        System.out.println("deleting: " + m.toString());
        delete(m);
    }

    @FXML
    void findMoneyModelByIdAndAdded(ActionEvent event) 
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter id: ");
        int id = input.nextInt();
        System.out.println("Enter money added: ");
        double added = input.nextDouble();
        
        List<MoneyModel> m = findMoneyModelByIdAndAdded(id, added);
    }

    @FXML
    void findMoneyModelByIdAndSpent(ActionEvent event) 
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter id: ");
        int id = input.nextInt();
        System.out.println("Enter money spent: ");
        double spent = input.nextDouble();
        
        List<MoneyModel> m = findMoneyModelByIdAndSpent(id, spent);
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
            System.out.println(m.getId() + " " + m.getAdded() + " " + m.getSpent());
            //code from demo
        }
    }
    EntityManager manager;  // code from demo

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        manager = (EntityManager) Persistence.createEntityManagerFactory("MaddieSchmittFXMLPU").createEntityManager();
        //code from demo

    }
    public void create(Moneymodel moneymodel)
    {
        try
        {
        manager.getTransaction().begin();
        if(moneymodel.getId() !=null)
        {
            manager.persist(moneymodel);
            manager.getTransaction().commit();
            System.out.println(moneymodel.toString());
        }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    private MoneyModel readMoneyModel(int id)
    {
        Query query = manager.createNamedQuery("MoneyModel.findById");
        query.setParameter("ID", id);
        MoneyModel moneymodel = (MoneyModel) query.getSingleResult();
        if(moneymodel !=null)
        {
            System.out.println(moneymodel.getId() + " " + moneymodel.getAdded() + " "
            + moneymodel.getSpent());
        }
        return moneymodel;
    }

    private void update(Moneymodel model)
    {
        try
        {
            MoneyModel exisitingMoneyModel = manager.find(MoneyModel.class, model.getId());
            if(exisitingMoneyModel !=null)
            {
                manager.getTransaction().begin();
                exisitingMoneyModel.setAdded(model.getAdded());
                exisitingMoneyModel.setSpent(model.getSpent());
                manager.getTransaction().commit();
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    private void delete(MoneyModel m)
    {
        try
        {
        MoneyModel exisitingMoneyModel = manager.find(MoneyModel.class, m.getId());
        if(exisitingMoneyModel !=null)
        {
            manager.getTransaction().begin();
            manager.remove(exisitingMoneyModel);
            manager.getTransaction().commit();
        }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

  public List<MoneyModel> findMoneyModelByIdAndSpent(int id, double spent)
  {
      Query query = manager.createNamedQuery("MoneyModel.findMoneyModelByIdAndSpent");
      query.setParameter("id", id);
      query.setParameter("spent",spent);
      
      List<MoneyModel> m = query.getResultList();
      for(MoneyModel moneymodel: m)
      {
          System.out.println(moneymodel.getId() + " " + moneymodel.getSpent() + " " + moneymodel.getAdded());        
      }
      return m;
  }

    private List<MoneyModel> findMoneyModelByIdAndAdded(int id, double added)
    {
      Query query = manager.createNamedQuery("MoneyModel.findMoneyModelByIdAndAdded");
      query.setParameter("id", id);
      query.setParameter("added",added);
      
      List<MoneyModel> m = query.getResultList();
      for(MoneyModel moneymodel: m)
      {
          System.out.println(moneymodel.getId() + " " + moneymodel.getAdded() + " " + moneymodel.getSpent());        
      }
      return m;
    }
   
  
}
