import MenuAppBar from "./MenuAppBar.tsx";
import Form from "./Form.tsx";
import {useState} from "react";

function RegistrationScreen() {

    const [authenticated, setAuthenticated] = useState(false);

    const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setAuthenticated(true);
    }

    return(
        <>
            <MenuAppBar>

            </MenuAppBar>
            <Form>

            </Form>
        </>
    );
}

export default RegistrationScreen;