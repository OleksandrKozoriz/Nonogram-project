import {useEffect, useState} from "react";
import MapElement, {MapProps} from "./Map/MapElement.tsx";

function Field(  ){
    const [myMap, setMap] = useState<MapProps>();

    const fetchMap = async () => {
        const response = await fetch("http://localhost:8081/map")
        const json = (await response.json()) as MapProps;
        setMap(json);
    }

    useEffect(() => {
        fetchMap();
    }, []);
    // console.log(myMap)
    // console.log(myMap);

    return (
        <>
            <MapElement colsNumber={myMap?.colsNumber} rowsNumber={myMap?.rowsNumber} horizontalField={myMap?.horizontalField} verticalField={myMap?.verticalField} pictureField={myMap?.pictureField} scorePoints={myMap?.scorePoints} solved={myMap?.solved}/>
        </>
    );
}

export default Field;