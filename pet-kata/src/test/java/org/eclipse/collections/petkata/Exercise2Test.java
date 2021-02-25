/*
 * Copyright (c) 2020 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.petkata;

import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.factory.Sets;
import org.eclipse.collections.impl.test.Verify;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * In the slides leading up to this exercise you should have learned about the following APIs.
 * <p/>
 * {@link MutableList#flatCollect(Function)}<br>
 * {@link MutableList#select(Predicate)}<br>
 * {@link MutableList#reject(Predicate)}<br>
 * {@link MutableList#count(Predicate)}<br>
 * {@link MutableList#detect(Predicate)}<br>
 * {@link MutableList#anySatisfy(Predicate)}<br>
 * {@link MutableList#allSatisfy(Predicate)}<br>
 * {@link MutableList#noneSatisfy(Predicate)}<br>
 * <br>
 * You should have also learned about the following methods as well.<br>
 * <br>
 * {@link MutableList#selectWith(Predicate2, Object)}<br>
 * {@link MutableList#rejectWith(Predicate2, Object)}<br>
 * {@link MutableList#countWith(Predicate2, Object)}<br>
 * {@link MutableList#detectWith(Predicate2, Object)}<br>
 * {@link MutableList#anySatisfyWith(Predicate2, Object)}<br>
 * {@link MutableList#allSatisfyWith(Predicate2, Object)}<br>
 * {@link MutableList#noneSatisfyWith(Predicate2, Object)}<br>
 * <br>
 * @see <a href="http://eclipse.github.io/eclipse-collections-kata/pet-kata/#/4">Exercise 2 Slides</a>
 */
public class Exercise2Test extends PetDomainForKata
{
    @Test
    @Tag("KATA")
    public void doAnyPeopleHaveCats()
    {
        Predicate<Person> predicate = person -> person.hasPet(PetType.CAT); //replace null with a Predicate lambda which checks for PetType.CAT
        Assertions.assertTrue(this.people.anySatisfy(predicate));
    }

    @Test
    @Tag("KATA")
    public void doAllPeopleHavePets()
    {
        Predicate<Person> predicate = Person::isPetPerson;
        boolean result = this.people.allSatisfy(predicate); //replace with a method call send to this.people that checks if all people have pets
        Assertions.assertFalse(result);
    }

    @Test
    @Tag("KATA")
    public void howManyPeopleHaveCats()
    {
        int count = this.people.countWith(Person::hasPet,PetType.CAT);
        Assertions.assertEquals(2, count);
    }

    @Test
    @Tag("KATA")
    public void findMarySmith()
    {
        Person result = this.people.detect(person -> person.named("Mary Smith"));
        Assertions.assertEquals("Mary", result.getFirstName());
        Assertions.assertEquals("Smith", result.getLastName());
    }

    @Test
    @Tag("KATA")
    public void getPeopleWithPets()
    {
        MutableList<Person> petPeople = this.people.select(Person::isPetPerson); // replace with only the pet owners
        Verify.assertSize(7, petPeople);
    }

    @Test
    @Tag("KATA")
    public void getAllPetTypesOfAllPeople()
    {
        Function<Person, Iterable<PetType>> function = Person::getPetTypes;
        MutableSet<PetType> petTypes = null;

        var expectedSet = Sets.mutable.with(PetType.CAT, PetType.DOG, PetType.TURTLE, PetType.HAMSTER, PetType.BIRD, PetType.SNAKE);
        Assertions.assertEquals(expectedSet, petTypes);
    }

    @Test
    @Tag("KATA")
    public void getFirstNamesOfAllPeople()
    {
        MutableList<String> firstNames = null;  // Transform this.people into a list of first names

        var expectedList = Lists.mutable.with("Mary", "Bob", "Ted", "Jake", "Barry", "Terry", "Harry", "John");
        Assertions.assertEquals(expectedList, firstNames);
    }

    @Test
    @Tag("KATA")
    public void doAnyPeopleHaveCatsRefactor()
    {
        boolean peopleHaveCatsLambda = this.people.anySatisfy(person -> person.hasPet(PetType.CAT));
        Assertions.assertTrue(peopleHaveCatsLambda);

        //use method reference, NOT lambdas, to solve the problem below
        boolean peopleHaveCatsMethodRef = this.people.anySatisfyWith(Person::hasPet,PetType.CAT);
        Assertions.assertTrue(peopleHaveCatsMethodRef);
    }

    @Test
    @Tag("KATA")
    public void doAllPeopleHaveCatsRefactor()
    {
        boolean peopleHaveCatsLambda = this.people.allSatisfy(person -> person.hasPet(PetType.CAT));
        Assertions.assertFalse(peopleHaveCatsLambda);

        //use method reference, NOT lambdas, to solve the problem below
        boolean peopleHaveCatsMethodRef = this.people.allSatisfyWith(Person::hasPet,PetType.CAT);
        Assertions.assertFalse(peopleHaveCatsMethodRef);
    }

    @Test
    @Tag("KATA")
    public void getPeopleWithCatsRefactor()
    {
        //use method reference, NOT lambdas, to solve the problem below
        MutableList<Person> peopleWithCatsMethodRef = this.people.selectWith(Person::hasPet,PetType.CAT);
        Verify.assertSize(2, peopleWithCatsMethodRef);
    }

    @Test
    @Tag("KATA")
    public void getPeopleWithoutCatsRefactor()
    {
        //use method reference, NOT lambdas, to solve the problem below
        MutableList<Person> peopleWithoutCatsMethodRef = this.people.rejectWith(Person::hasPet,PetType.CAT);
        Verify.assertSize(6, peopleWithoutCatsMethodRef);
    }
}
