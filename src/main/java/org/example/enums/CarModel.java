package org.example.enums;

import java.util.Optional;

public enum CarModel {
    LADA_VESTA("Lada Vesta"),
    LADA_GRANTA("Lada Granta"),
    RENAULT_LOGAN("Renault Logan"),
    TOYOTA_CAMRY("Toyota Camry"),
    MAZDA_6("Mazda 6"),
    KIA_RIO("Kia Rio"),
    FORD_FOCUS("Ford Focus"),
    AUDI_A4("Audi A4"),
    VOLKSWAGEN_PASSAT("Volkswagen Passat");

    private final String modelName;

    CarModel(String modelName) {
        this.modelName = modelName;
    }

    public String getModelName() {
        return modelName;
    }

    @Override
    public String toString() {
        return modelName;
    }

    //    Метод для получения enum по строке
    public static Optional<CarModel> fromString(String text) {
        for (CarModel model : CarModel.values()) {
//            TODO: Нужна ли валидация аргумента text?
            if (model.modelName.equalsIgnoreCase(text)) {
                return Optional.of(model);
            }
        }
        return Optional.empty();
    }

    public static void main(String[] args) {
        Optional<CarModel> input1 = CarModel.fromString("Lada granta");
        Optional<CarModel> input2 = CarModel.fromString("Ford Focus");
        Optional<CarModel> input3 = CarModel.fromString("ford focus");
        Optional<CarModel> input4 = CarModel.fromString("ferrari");

//        Collections.sort();

        System.out.println(input1.map(CarModel::getModelName).orElse("Не найдено"));
        System.out.println(input2.map(CarModel::getModelName).orElse("Не найдено"));
        System.out.println(input3.map(CarModel::getModelName).orElse("Не найдено"));
        System.out.println(input4.map(CarModel::getModelName).orElse("Не найдено"));
    }
}