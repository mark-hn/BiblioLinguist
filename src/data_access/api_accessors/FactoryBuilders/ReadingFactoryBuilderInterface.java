package data_access.api_accessors.FactoryBuilders;

import entity.reading.ReadingType;
import entity.reading.factory.DifficultyReadingFactory;

public interface ReadingFactoryBuilderInterface {
    DifficultyReadingFactory getReadingFactory(ReadingType readingType);
}
