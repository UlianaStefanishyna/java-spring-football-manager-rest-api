CREATE TABLE IF NOT EXISTS team
(
  id         SERIAL8      NOT NULL,
  name       VARCHAR(255) NOT NULL,
  captain_id BIGINT ,

  CONSTRAINT team_id_PK PRIMARY KEY (id),
  CONSTRAINT team_captain_id_UQ UNIQUE (captain_id)
);

CREATE TABLE IF NOT EXISTS player
(

  id         SERIAL8      NOT NULL,
  first_name VARCHAR(255) NOT NULL,
  last_name  VARCHAR(255) NOT NULL,
  position   int          NOT NULL,
  birthday   DATE         NOT NULL,
  team_id    BIGINT,

  CONSTRAINT player_id_PK PRIMARY KEY (id),
  CONSTRAINT player_team_team_id_FK FOREIGN KEY (team_id) REFERENCES team (id)
);

ALTER TABLE team
  DROP CONSTRAINT IF EXISTS team_player_captain_id_FK;

ALTER TABLE team
  ADD CONSTRAINT team_player_captain_id_FK FOREIGN KEY (captain_id) REFERENCES player (id);