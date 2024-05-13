import {useEffect, useState} from "react";
import MenuAppBar from "./MenuAppBar.tsx";
import CommentSend from "./CommentSend.tsx";
// import {MapProps} from "./Map/MapElement.tsx";

interface CommentProps {
    id: number,
    comment: string
    commented_on : number
    game : string
    player : string
}

function Comments() {
    const [comments, setComments] = useState<CommentProps[]>([]);

    const fetchComments = async () => {
        const response = await fetch("http://localhost:8081/api/comment/nonogram");
        const json = (await response.json()) as CommentProps[];
        setComments(json);
    }

    useEffect(() => {
        fetchComments();
    }, [])

    const sendComment = async (comment: CommentProps) => {
        const response = await fetch("http://localhost:8081/api/comment", {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(comment)
        })
        fetchComments();
        console.log(response);
    }

    return (
        <div className="Container">
            <MenuAppBar></MenuAppBar>

            <div className="d-flex ">
                {comments.map((comment) =>
                    <div
                        className="card"
                        key={comment.id}
                        style={{width: '18rem', padding: '0.5rem'}}
                    >
                        <h5 className="card-title">{comment.player}</h5>
                        <h6 className="card-subtitle mb-2 text-body-secondary">{comment.commented_on}</h6>
                        <p className="card-text">{comment.comment}</p>
                    </div>
                )}
                <CommentSend onSubmit={sendComment} ></CommentSend>
            </div>
        </div>
    );
}

export default Comments;