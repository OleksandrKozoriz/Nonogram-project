import {CSSProperties} from "react";
import Square from "../Square.tsx";

interface SideFieldProps {
    sideField: number[][] | undefined;
    style : CSSProperties;
}

function SideField(props : SideFieldProps) {

    const cols = props.sideField?.[0].length;
    const rows = props.sideField?.length;

    const gridStyles = {
        display: 'grid',
        gridTemplateColumns: `repeat(${cols}, auto)`, // Setting number of columns
        gridTemplateRows: `repeat(${rows}, auto)`, // Setting number of rows
    };

    return(
        <div className="sideField" style={{...props.style, ...gridStyles}} >
            {
                props.sideField?.map((row, rowIdx) =>
                    row.map((col, colIdx) =>
                        <Square
                            row={rowIdx+1}
                            col={colIdx+1}
                            key = {rowIdx - colIdx}
                            onClick={ () => {} }
                            style={undefined}
                        >
                            {col}
                        </Square>
                    )
                )
            }
        </div>
    );
}

export default SideField;