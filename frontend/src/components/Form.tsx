import React, {useState} from "react";
import {Link} from "react-router-dom";

function Form(){
    const [name, setName] = useState('');
    // const [email, setEmail] = useState('');

    // Function to handle form submission
    const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        console.log('Name:', name);
    };

    return (
        <div className="Container">

            <div className="container">
                <h1>Login to play</h1>
                <form onSubmit={handleSubmit}>
                    <div className="form-group">
                        <label htmlFor="name">Name</label>
                        <input
                            type="text"
                            className="form-control"
                            id="name"
                            value={name}
                            onChange={(e) => setName(e.target.value)}
                            placeholder="Enter your name"
                        />
                    </div>
                    <button type="submit" className="flex-wrap bo">
                        <Link to={'/game'} className="btn btn-primary">
                            Submit
                        </Link>
                    </button>
                </form>
            </div>
        </div>
    );
}

export default Form;