import {createBrowserRouter, RouterProvider} from "react-router-dom";
import Game from "./components/Game.tsx";
import Comments from "./components/Comments.tsx";
// import RegistrationScreen from "./components/RegistrationScreen.tsx";

const router = createBrowserRouter([
    {
        path: '/',
        element: <Game></Game>
    },
    {
        path: '/comments',
        element: <Comments></Comments>
    }
]);

function App() {
    return (
        <RouterProvider router={router} />
    );
}

export default App;