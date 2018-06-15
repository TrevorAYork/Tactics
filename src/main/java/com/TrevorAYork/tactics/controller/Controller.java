package com.TrevorAYork.tactics.controller;

import com.TrevorAYork.tactics.model.Move;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class Controller {

    @RequestMapping(value = "/getId", method = RequestMethod.GET)
    public UUID getCharId() {
        return UUID.randomUUID();
    }

    @RequestMapping(value = "/move/{direction}/{x}/{y}", method = RequestMethod.POST)
    public Move move(@PathVariable("direction") String direction, @PathVariable("x") double x, @PathVariable("y") double y) {
        Move move;
        if (direction.equals("left"))
            move = new Move(x - 3, y);
        else if (direction.equals("right"))
            move = new Move(x + 3, y);
        else if (direction.equals("down"))
            move = new Move(x, y + 3);
        else if (direction.equals("up"))
            move = new Move(x, y - 3);
        else
            return new Move(x, y);

        if (isValid(move)){
            return move;
        }
        return new Move(x, y);
    }

    private boolean isValid(Move move) {
        if (move.getX() > 752 || move.getX() < 16)
            return false;
        if (move.getY() > 736 || move.getY() < 16)
            return false;

        return true;
    }
}
