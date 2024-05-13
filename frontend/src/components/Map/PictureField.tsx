import {CSSProperties, useEffect, useState} from "react";
import Square from "../Square.tsx";

interface PictureFieldProps{
    pictureField: { state : string; }[][] | undefined;
    style: CSSProperties;
}

interface Coordinates{
    XFrom: number,
    YFrom: number,
    XTo: number,
    YTo: number,
    state: string;
}

function PictureField(props: PictureFieldProps) {
    const [Field, setField] = useState(props.pictureField);
    const [isSolved, setIsSolved] = useState<boolean>(false);

    const cols = props.pictureField?.[0].length;
    const rows = props.pictureField?.length;

    const checkMap = async (coordinates: Coordinates) => {
        const responce = await fetch('http://localhost:8081/map/put', {
            method: "PUT",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(coordinates)
        })
        const json = (await responce.json()) as boolean;
        setIsSolved(json);
    }

    const gridStyles = {
        display: "grid",
        gridTemplateColumns: `repeat(${cols}, auto)`,
        gridTemplateRows: `repeat(${rows}, auto)`,
        ...props.style
    }

    useEffect(() => {
        setField(props.pictureField);
    }, [props.pictureField]);

    const squareStyle = (element: { state: string }) => {
        switch (element.state) {
            case "WHITE":
                return { backgroundColor: "white" };
            case "COLORED":
                return { backgroundColor: "black" };
            case "CROSSED":
                return { backgroundColor: "cornflowerblue" };
            default:
                return {}; // Default empty style
        }
    };

    const changeState = (element: {state: string}, row: number, col: number) => {
        const newState = element.state === "WHITE"
            ? "COLORED"
            : element.state === "COLORED"
                ? "CROSSED"
                : "WHITE";

        setField(prevField => {
            if (!prevField) return prevField; // Return undefined if prevField is undefined

            const updatedField = [...prevField]; // Create a shallow copy of the field
            updatedField[row][col].state = newState;
            return updatedField;
        });

        const coordinates: Coordinates = {
            XFrom: row,
            YFrom: col,
            XTo: row,
            YTo: col,
            state: newState,
        };

        console.log(coordinates)
        checkMap(coordinates)
    }
    
    return(
        <>
            {isSolved ? <div>Solved!</div> :
            <div className="pictureField" style={gridStyles}>
                {
                    Field?.map((row, rowIdx) =>
                        row.map((element, colIdx) =>
                            <Square
                                row={rowIdx+1}
                                col={colIdx+1}
                                key = {rowIdx - colIdx}
                                onClick={
                                    () => changeState(element, rowIdx, colIdx)
                                }
                                style={squareStyle(element)}
                            >
                                {undefined}
                            </Square>
                        )
                    )
                }
            </div>
            }
        </>
    );
}

export default PictureField;