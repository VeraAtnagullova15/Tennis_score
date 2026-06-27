package ru.atnagullova.tennis_score.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import ru.atnagullova.tennis_score.match_state.ActiveMatch;
import ru.atnagullova.tennis_score.model.MatchScoreModel;

@Mapper
public interface MatchScoreMapper {

    MatchScoreMapper INSTANCE = Mappers.getMapper(MatchScoreMapper.class);

    @Mapping(source = "playerOneScore.set", target = "playerOneSets")
    @Mapping(source = "playerTwoScore.set", target = "playerTwoSets")
    @Mapping(source = "playerOneScore.game", target = "playerOneGames")
    @Mapping(source = "playerTwoScore.game", target = "playerTwoGames")
    @Mapping(target = "playerOnePoints", qualifiedByName = "convertPoints", source = "playerOneScore.point")
    @Mapping(target = "playerTwoPoints", qualifiedByName = "convertPoints", source = "playerTwoScore.point")
    MatchScoreModel toMatchScoreModel(ActiveMatch activeMatch);

    @Named("convertPoints")
    default String convertPoints(int point) {

            return switch (point) {
                case 0 -> "0";
                case 1 -> "15";
                case 2 -> "30";
                case 3 -> "40";
                case 4 -> "AD";
                default -> "0";
            };
    }
}
