package com.meli.tucasita.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.meli.tucasita.dto.district.DistrictDTO;
import com.meli.tucasita.dto.property.PropertyDTO;
import com.meli.tucasita.dto.environment.EnvironmentDTO;
import com.meli.tucasita.model.District;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;

public class TestUtilsGenerator {

    private static String SCOPE;
    private static String fileName;
    private static ObjectWriter mapper;

    public static void emptyDistrictsFile() {
        Properties properties = new Properties();
        try {
            properties.load(new ClassPathResource("application.properties").getInputStream());
            SCOPE = properties.getProperty("api.scope");
            fileName = properties.getProperty("repository.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter writer = null;

        try {
            writer = new PrintWriter(ResourceUtils.getFile("./src/" + SCOPE + "/resources/"+fileName));
        } catch (
                IOException e) {
            e.printStackTrace();
        }

        writer.print("[]");
        writer.close();
    }

    public static void generateInvalidJsonFileFormat() {
        Properties properties = new Properties();
        try {
            properties.load(new ClassPathResource("application.properties").getInputStream());
            SCOPE = properties.getProperty("api.scope");
            fileName = properties.getProperty("repository.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter writer = null;

        try {
            writer = new PrintWriter(ResourceUtils.getFile("./src/" + SCOPE + "/resources/"+fileName));
        } catch (
                IOException e) {
            e.printStackTrace();
        }

        writer.print(",[]}");
        writer.close();
    }

    public static District getDistrict(String name1,double builtPrice,double unbuiltPrice){
        District aDistrict= new District(name1,builtPrice,unbuiltPrice);
        return aDistrict;
    }

    public static District getDistrict(String name1){
        District aDistrict= new District(name1,50.0,50.0);
        return aDistrict;
    }

    public static List<District> getValidsDistricts() {
        List<District> districts=new ArrayList<>();
        District aDistricta= new District("Belgrano",100.0,100.0);
        District aDistrictb= new District("Balvanera",100.0,100.0);
        District aDistrictc= new District("Palermo",100.0,100.0);
        District aDistrictd= new District("Centro",100.0,100.0);
        District aDistricte= new District("Urquiza",100.0,100.0);
        districts.add(aDistricta);
        districts.add(aDistrictb);
        districts.add(aDistrictc);
        districts.add(aDistrictd);
        districts.add(aDistricte);
        return districts;
    }

    public static void loadDataInJson(List<District> districts){
        emptyDistrictsFile();
        for(District d:districts){
            appendNewDistrict(d);
        }
    }

    public static void loadDefaultDataInJson(){
        emptyDistrictsFile();
        for(District d:getValidsDistricts()){
            appendNewDistrict(d);
        }
    }

    public static PropertyDTO getValidProperty(){
        double widthLand= 40;
        double lengthLand=50;
        double widthRoom=25;
        double lengthRoom=33;
        District aDistrict= TestUtilsGenerator.getValidsDistricts().get(0);
        DistrictDTO district = new DistrictDTO(aDistrict.getName(),aDistrict.getBuiltPrice(),aDistrict.getUnbuiltPrice());
        List<EnvironmentDTO> rooms =new ArrayList<>(
                Arrays.asList(new EnvironmentDTO("Habitacion1",widthRoom,lengthRoom),
                        new EnvironmentDTO("Habitacion2",widthRoom,lengthRoom)));

        return new PropertyDTO(district.getName(),"Propiedad",widthLand,lengthLand,rooms);
    }

    public static PropertyDTO getInvalidPropertyWEnvironmentsSizesSettings(){
        double widthLand= 40;
        double lengthLand=50;
        double widthRoom=25;
        double lengthRoom=33;
        District aDistrict=TestUtilsGenerator.getValidsDistricts().get(0);
        DistrictDTO district = new DistrictDTO(aDistrict.getName(),aDistrict.getBuiltPrice(),aDistrict.getUnbuiltPrice());
        List<EnvironmentDTO> rooms =new ArrayList<>(
                Arrays.asList(new EnvironmentDTO("Habitacion1",widthRoom,lengthRoom),
                        new EnvironmentDTO("Habitacion2",widthRoom,lengthRoom),
                        new EnvironmentDTO("Habitacion3",widthRoom,lengthRoom)));

        return new PropertyDTO(district.getName(),"Propiedad",widthLand,lengthLand,rooms);
    }

    public static DistrictDTO getValidDistrict(){
        District district= TestUtilsGenerator.getValidsDistricts().get(0);
        return new DistrictDTO(district.getName(),district.getBuiltPrice(),district.getUnbuiltPrice());
    }

    public static PropertyDTO getPropertyWInvalidDistrictSettings(){
        double widthLand= 40;
        double lengthLand=50;
        double widthRoom=25;
        double lengthRoom=33;
        List<EnvironmentDTO> rooms =new ArrayList<>(
                Arrays.asList(new EnvironmentDTO("Habitacion1",widthRoom,lengthRoom),
                        new EnvironmentDTO("Habitacion2",widthRoom,lengthRoom)));

        return new PropertyDTO("BarrioInexistente","Propiedad",widthLand,lengthLand,rooms);
    }


    public static void appendNewDistrict(District district) {
        mapper = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        PrintWriter writer = null;

        try {
            String content = Files.readString(new File("./src/" + SCOPE + "/resources/"+fileName).getAbsoluteFile().toPath(), StandardCharsets.UTF_8);
            writer = new PrintWriter(ResourceUtils.getFile("./src/" + SCOPE + "/resources/"+fileName));

            try {
                String districtAsString = mapper.writeValueAsString(district);
                writer.print(content.substring(0, content.length()-1));
                if (content.length()>2) writer.print(", ");
                writer.print(districtAsString);
                writer.print("]");
            } catch (JsonProcessingException jsonProcessingException) {
                jsonProcessingException.printStackTrace();
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        writer.close();
    }

}
