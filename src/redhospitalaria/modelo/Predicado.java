/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package redhospitalaria.modelo;

import java.io.Serializable;
import java.util.function.Predicate;

/**
 *
 * @author david
 * @param <E>
 */

@FunctionalInterface
public interface Predicado<E> extends Predicate<E>, Serializable{
}
