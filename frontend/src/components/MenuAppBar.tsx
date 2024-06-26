import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
// import Typography from '@mui/material/Typography';
import IconButton from '@mui/material/IconButton';
import MenuIcon from '@mui/icons-material/Menu';
import {Link} from "react-router-dom";



export default function MenuAppBar() {
    const [auth, setAuth] = React.useState(true);
    // const [anchorEl, setAnchorEl] = React.useState<null | HTMLElement>(null);
    //
    // const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    //     setAuth(event.target.checked);
    // };
    //
    // const handleMenu = (event: React.MouseEvent<HTMLElement>) => {
    //     setAnchorEl(event.currentTarget);
    // };
    //
    // const handleClose = () => {
    //     setAnchorEl(null);
    // };

    return (
        <Box sx={{ flexGrow: 1 }}>
            <AppBar position="static">
                <Toolbar>

                    <IconButton
                        size="large"
                        edge="start"
                        color="inherit"
                        aria-label="menu"
                        sx={{ mr: 2 }}
                    >
                        <MenuIcon />
                    </IconButton>

                    <Link to = "/comments" className="link">
                        Comments
                    </Link>

                    {auth && (
                        <Link to="/" className="link">
                            Game
                        </Link>
                    )}
                </Toolbar>
            </AppBar>
        </Box>
    );
}