import React, { useState } from 'react';
// import axios from 'axios';
// npm install axios

interface CommentFormProps {
    onSubmit: (comment: CommentProps) => void;
}

interface CommentProps {
    id: number;
    comment: string;
    commented_on: number;
    game: string;
    player: string;
}

function CommentForm( props: CommentFormProps ) {
    const [comment, setComment] = useState('');
    const [game, setGame] = useState('');
    const [player, setPlayer] = useState('');

    const handleSubmit = async (event: React.FormEvent) => {
        event.preventDefault();

        try {
            const newComment: CommentProps = {
                id: Date.now(), // Generate a temporary id
                comment,
                commented_on: Date.now(), // Add timestamp for when the comment was made
                game,
                player,
            };

            // Simulate sending the comment to the server
            // Replace this with your actual server endpoint
            // await axios.post('your-server-endpoint', newComment);

            // Clear the form fields after submitting
            setComment('');
            setGame('');
            setPlayer('');

            // Call the onSubmit function with the new comment
            props.onSubmit(newComment);
        } catch (error) {
            console.error('Error submitting comment:', error);
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <div>
                <label htmlFor="comment">Comment:</label>
                <textarea id="comment" value={comment} onChange={(e) => setComment(e.target.value)} />
            </div>
            <div>
                <label htmlFor="game">Game:</label>
                <input type="text" id="game" value={game} onChange={(e) => setGame(e.target.value)} />
            </div>
            <div>
                <label htmlFor="player">Player:</label>
                <input type="text" id="player" value={player} onChange={(e) => setPlayer(e.target.value)} />
            </div>
            <button type="submit">Submit</button>
        </form>
    );
};

export default CommentForm;