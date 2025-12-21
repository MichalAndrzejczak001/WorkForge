from fastapi import FastAPI, HTTPException
from pydantic import BaseModel, Field
from app.chat_service import improve_text, find_gaps

app = FastAPI(title="Text Improvement API", version="1.0.0")


class ImproveTextRequest(BaseModel):
    text: str = Field(
        ...,
        min_length=1,
        max_length=5000,
        description="Tekst do poprawy stylistycznej i gramatycznej"
    )

class FindGapsRequest(BaseModel):
    text: str = Field(
        ...,
        min_length=1,
        max_length=5000,
        description="Tekst oferty pracy w którym należy znaleźć błędy."
    )


class ImproveTextResponse(BaseModel):
    text: str

class FindGapsResponse(BaseModel):
    text: str

@app.get("/health")
def health_check():
    return {"status": "OK"}

@app.post("/improve-text", response_model=ImproveTextResponse)
async def improve_text_endpoint(req: ImproveTextRequest):
    try:
        improved = await improve_text(req.text)
        return ImproveTextResponse(text=improved)
    except Exception as e:
        raise HTTPException(
            status_code=502,
            detail=f"Error during text improvment: {str(e)}"
        )

@app.post("/find-gaps", response_model=FindGapsResponse)
async def find_gaps_endpoint(req: FindGapsRequest):
    try:
        improved = await find_gaps(req.text)
        return FindGapsResponse(text=improved)
    except Exception as e:
        raise HTTPException(
            status_code=502,
            detail=f"Error during try of finding gaps in text: {str(e)}"
        )

