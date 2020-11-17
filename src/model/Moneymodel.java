/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author chees
 */
@Entity
@Table(name = "MONEYMODEL")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Moneymodel.findAll", query = "SELECT m FROM Moneymodel m")
    , @NamedQuery(name = "Moneymodel.findById", query = "SELECT m FROM Moneymodel m WHERE m.id = :id")
    , @NamedQuery(name = "Moneymodel.findByAdded", query = "SELECT m FROM Moneymodel m WHERE m.added = :added")
    , @NamedQuery(name = "Moneymodel.findBySpent", query = "SELECT m FROM Moneymodel m WHERE m.spent = :spent")
    , @NamedQuery(name = "Moneymodel.findByIdAndAdded", query = "SELECT m FROM Moneymodel m WHERE m.id = :id and m.added = :added")
    , @NamedQuery(name = "Moneymodel.findByIdAndSpent", query = "SELECT m FROM Moneymodel m WHERE m.id = :id and m.spent = :spent")
})
public class Moneymodel implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ADDED")
    private Double added;
    @Column(name = "SPENT")
    private Double spent;

    public Moneymodel()
    {
    }

    public Moneymodel(Integer id)
    {
        this.id = id;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Double getAdded()
    {
        return added;
    }

    public void setAdded(Double added)
    {
        this.added = added;
    }

    public Double getSpent()
    {
        return spent;
    }

    public void setSpent(Double spent)
    {
        this.spent = spent;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Moneymodel))
        {
            return false;
        }
        Moneymodel other = (Moneymodel) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "model.Moneymodel[ id=" + id + " ]";
    }
    
}
