/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaces;

import entities.Person;

/**
 *
 * @author colenso
 */
public interface PersonDAOInterface {
    public Person findPersonByName(String name);
}
