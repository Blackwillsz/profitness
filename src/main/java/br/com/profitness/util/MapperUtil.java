package br.com.profitness.util;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class MapperUtil {

    private static ModelMapper mapper = null;

    private MapperUtil(){}

    public static ModelMapper getMapper(){
        if (mapper == null){
            mapper = new ModelMapper();
        }
        return mapper;
    }

    public static <T> T converte(Object origem, Class<T> destino) {
        return MapperUtil.getMapper().map(origem, destino);
    }
}
