package businessLogic;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import businessLogic.validators.EmailValidator;

import businessLogic.validators.Validator;
import dataAccess.AbstractDAO;
import dataAccess.ClientDAO;
import model.Client;

import static dataAccess.ClientDAO.insert;


public class ClientBLL {
    private List<Validator<Client>> validators;

    public ClientBLL() {
        validators = new ArrayList<Validator<Client>>();
        validators.add(new EmailValidator());
    }

    /**
     * Gaseste un client in baza de date dupa ID.
     *
     * @param id ID-ul clientului de cautat in baza de date
     * @return obiectul de tip Client cu ID-ul specificat
     * @throws NoSuchElementException daca nu exista un client cu ID-ul specificat în baza de date
     */
    public Client findStudentById(int id) {
        Client st = ClientDAO.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The student with id =" + id + " was not found!");
        }
        return st;
    }

    /**
     * Insereaza un nou client in baza de date și valideaza datele acestuia folosind
     * un set de obiecte Validator specificate.
     *
     * @param client obiectul Client de inserat
     * @return id-ul generat pentru clientul inserat
     */
    public int insertStudent(Client client) {
        for (Validator<Client> v : validators) {
            v.validate(client);
        }
        return ClientDAO.insert(client);
    }

    /**
     * Editeaza un nou client in baza de date și valideaza datele acestuia folosind
     * un set de obiecte Validator specificate.
     *
     * @param client obiectul Client de editat
     * @return id-ul generat pentru clientul editat
     */
    public int editInsertStudent(Client client) {
        for (Validator<Client> v : validators) {
            v.validate(client);
        }
        return ClientDAO.editInsert(client);
    }

    /**
     * Sterge un client din baza de date.
     *
     * @param client numele clientului de sters
     */
    public void deleteStudent(String client) {
        ClientDAO.delete(client);
    }

    /**
     * Returneaza o lista de nume ale clientilor care pot fi stersi din baza de date.
     *
     * @return o lista de nume ale clientilor care pot fi stersi din baza de date
     */
    public List<String> listNamesDelete()
    {
        List<String>names=ClientDAO.listNames();
         return names;
    }

    /**
     * Returneaza o lista cu toti clientii din baza de date.
     *
     * @return o lista cu toti clientii din baza de date
     */
    public List<Client> findAllClients()
    {
        List<Client> allClients=ClientDAO.findAllClients();
        return allClients;
    }
}
