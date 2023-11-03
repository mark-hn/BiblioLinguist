package data_access.APIAccessors;

import entity.DifficultyLevel;
import entity.language.Language;
import entity.reading.AIGeneratedStory;
import entity.reading.Reading;

public interface AIGeneratedStoryRetriever extends ReadingRetrieverInterface
{
    @Override
    AIGeneratedStory retrieveReadingFromAPI(Language language);
    AIGeneratedStory retrieveReadingFromAPI(Language language, DifficultyLevel difficulty);
    AIGeneratedStory[] retrieveListOfStoriesFromAPI(Language language, DifficultyLevel difficulty, Integer numOfStories);
}
