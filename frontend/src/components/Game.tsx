import MenuAppBar from "./MenuAppBar.tsx";
import Field from "./Field.tsx";

function Game() {
    return(
        <div className = 'Container'>
            <MenuAppBar></MenuAppBar>
            <Field></Field>
        </div>
    );
}

export default Game;