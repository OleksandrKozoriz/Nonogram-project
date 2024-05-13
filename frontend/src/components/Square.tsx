import {CSSProperties, ReactNode} from "react";

interface Pos{
    children: ReactNode | undefined;
    col:number;
    row:number;
    style: CSSProperties|undefined;
    onClick: () => void;
}

function Square( props: Pos ) {

    const gridPosition = {
        gridRow : props.row,
        gridColumn: props.col,
    }

    return (
        <div
            className="square"
            style={{...gridPosition, ...props.style}}
            onClick={props.onClick}
        >
            {props.children}
        </div>
    );
}

export default Square;