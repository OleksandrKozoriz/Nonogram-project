package sk.tuke.nonogram.server.webservice;

import org.springframework.web.bind.annotation.*;
import sk.tuke.nonogram.consoleui.Coordinates;
import sk.tuke.nonogram.core.Map;
import sk.tuke.nonogram.core.TileState;

import java.awt.*;
import java.io.FileNotFoundException;

@CrossOrigin
@RestController
@RequestMapping("/map")
public class MapControllerRest {
    Map map = new Map("/testNonogram.json");

    public MapControllerRest() throws FileNotFoundException {
    }

    @GetMapping
    public Map getMap() throws FileNotFoundException {
        return map;
    }

    @PutMapping("/put")
    public Boolean setColor(@RequestBody Coordinates coordinates){
        map.changeTilesState(coordinates);
        //        return map;
        return map.isSolved();
    }

}
