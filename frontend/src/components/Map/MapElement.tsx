import SideField from "./SideField.tsx";
import PictureField from "./PictureField.tsx";

export interface MapProps{
    colsNumber: number | undefined;
    rowsNumber: number | undefined;
    horizontalField: number[][] | undefined;
    verticalField: number[][] | undefined;
    pictureField: { state : string; }[][]| undefined;
    scorePoints: number | undefined;
    solved: boolean | undefined;
}

function MapElement(props: MapProps){

    const gridStyles = {
        paddingTop: "10px",
        display: 'grid',
        gridTemplateColumns: `auto auto`, // Setting number of columns
        gridTemplateRows: `auto auto`, // Setting number of rows
        gridAutoFlow: 'dense',
        gridGap: '5px',
        width: 'min-content',
        height: 'min-content',
    };

    return(
        <>
            <div style={gridStyles} >
                <SideField sideField={props.horizontalField} style={{ gridRow:1, gridColumn:2 }}/>
                <SideField sideField={props.verticalField} style={{ gridRow:2, gridColumn:1 }}/>
                <PictureField pictureField={props.pictureField} style={{ gridRow:2, gridColumn:2 }} />
            </div>
        </>
    )
}

export default MapElement;