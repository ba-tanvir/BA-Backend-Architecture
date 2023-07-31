package com.example.demotest.service;


import com.example.demotest.dto.CarDTO;
import com.example.demotest.dto.IRequestDTO;
import com.example.demotest.entity.BaseEntity;
import com.example.demotest.entity.Car;
import com.example.demotest.entity.Movie;
import com.example.demotest.exception.ServiceExceptionHolder;
import com.example.demotest.repository.BaseRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.*;
import java.util.stream.Collectors;

@Data
@RequiredArgsConstructor
public abstract class BaseService<E extends BaseEntity, D extends IRequestDTO> {

    //Get the needed repository and model mapper
    private final BaseRepository<E> generic_repository;
    private final ModelMapper modelMapper;

    public List<D> get_all(){
        List<E> newList= new ArrayList<>();
        newList=generic_repository.findAll();
        System.out.println("This is Entity List ______==========");
        System.out.println(newList);
        System.out.println("This is DTO list --------============");
        System.out.println(newList.stream().map(this::getDto).collect(Collectors.toList()));
        return newList.stream().map(this::getDto).collect(Collectors.toList());
    }

    public D create(E new_item) {
        generic_repository.save(new_item);
        return modelMapper.map(new_item,getDtoClass());
    }
    public List<D> contains_name(String param){
        System.out.println("It is getting executed");
        List<E> new_list=new ArrayList<>();
        new_list=generic_repository.findAll();
        List<E> return_list=new ArrayList<>();
        for(E item:new_list){
            if(item.getName().contains(param)){
                return_list.add(item);
            }
        }
        return return_list.stream().map(this::getDto).collect(Collectors.toList());
    }

    public List<D> sort(String field_name){
        System.out.println("Sort is working");
        List<E> new_list=new ArrayList<>();
        new_list=generic_repository.findAll();
        Collections.sort(new_list,Comparator.comparing(E::getName));
        return new_list.stream().map(this::getDto).collect(Collectors.toList());
    }



    //Generic sorting algorithm works with any entity any field
    public List<D> sort_by_string(String field_name) throws NoSuchMethodException {
        //Creating each type of base type arrays.
        //Will use it based on the field type.
        List<Double> double_value_container=new ArrayList<>();
        List<String> string_value_container=new ArrayList<>();
        List<Integer> integer_value_container=new ArrayList<>();
        String type="Hhh"; //will contain the type of field

        //Declaring necessary Arrays
        List<E> new_list=new ArrayList<>();   //Will hold the repository
        List<E> final_list=new ArrayList<>(); //Will be returned
        //Getting everything from generic_repository
        new_list=generic_repository.findAll();

        //This List will contain all the field value
        //Using Java Reflect
        //Get the class name
        Class clazz=new_list.get(0).getClass();
        //Create the method name from parameter
        String final_name="get"+field_name.substring(0,1).toUpperCase()+field_name.substring(1);

        //get the declared method name
        //Add the value of field for sorting
        for(E item:new_list) {
            try {
                Method methodGetField = clazz.getDeclaredMethod(final_name);

                type=(methodGetField.invoke(item).getClass().getSimpleName());


                if(type.equals("Double")){
                    Double field_value=(Double) methodGetField.invoke(item);

                    double_value_container.add(field_value);
                }
                if(type.equals("String")){
                    String field_value=(String) methodGetField.invoke(item);

                    string_value_container.add(field_value);
                }

                if(type.equals("Integer")){
                    Integer field_value=(Integer) methodGetField.invoke(item);

                    integer_value_container.add(field_value);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        ///Sorting value field array based on type
        if(type.equals("Double")){
            Collections.sort(double_value_container);
            System.out.println(double_value_container);
        }

        if(type.equals("String")){
            Collections.sort(string_value_container);
            System.out.println(string_value_container);
        }

        if(type.equals("Integer")){
            Collections.sort(integer_value_container);
        }


        //This part will map the List of item to Sorted value container
        //In turn it will get sorted.
        //From value container it will match to the list
        //And remove from value container to avoid repetition.
        int i,j;
       //Compare the sort value with the list and enter in order
        for( i=0;i<new_list.size();i++){
            for( j=0;j< new_list.size();j++){
                try {
                    Method methodGetField = clazz.getDeclaredMethod(final_name);
                    //Double
                    if(type.equals("Double")) {
                        Double field_value = (Double) methodGetField.invoke(new_list.get(j));
                        if (Objects.equals(field_value, double_value_container.get(i))) {
                                System.out.println("Field Value" + field_value);
                                System.out.println("Container value is "+ double_value_container.get(i));
                                if(!final_list.contains(new_list.get(j))) {
                                    final_list.add(new_list.get(j));
                                }
                                double_value_container.contains(field_value);


                        }
                    }
                    //String
                    if(type.equals("String")) {
                        String field_value = (String) methodGetField.invoke(new_list.get(j));
                        if (Objects.equals(field_value, string_value_container.get(i))) {
                            if(!final_list.contains(new_list.get(j))) {
                                final_list.add(new_list.get(j));
                            }
                        }
                    }
                    //integer
                    if(type.equals("Integer")) {
                        Integer field_value = (Integer) methodGetField.invoke(new_list.get(j));
                        if (Objects.equals(field_value, integer_value_container.get(i))) {
                            if(!final_list.contains(new_list.get(j))) {
                                final_list.add(new_list.get(j));
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        //Sorted array based on field is returned in data transfer object criteria.
        return final_list.stream().map(this::getDto).collect(Collectors.toList());
    }




    //Parameterized is class that deals with other objects without knowing it's type
    @SuppressWarnings("unchecked")
    private Class<D> getDtoClass() {
        return (Class<D>) (((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1]);
    }
    private D getDto(E e){
        return modelMapper.map(e,getDtoClass());
    }


    public E trimStringValues(E paramObject) {
        System.out.println("This is Trim String Values Function");
        for (Field field : paramObject.getClass().getDeclaredFields()) {
            try {
                //field contains the entity field name
                field.setAccessible(true);

                //Gets the value of the field
                Object value=field.get(paramObject);
                System.out.println(value);

                if (value != null) {
                    if (value instanceof String) {
                        String trimmed = (String) value;
                        field.set(paramObject, trimmed.trim());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return paramObject;
    }
    }


