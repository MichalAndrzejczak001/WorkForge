import os
from dotenv import load_dotenv
from openai import AsyncOpenAI

# Wczytanie zmiennych środowiskowych
load_dotenv()

OPENAI_API_KEY = os.getenv("OPENAI_API_KEY")
if not OPENAI_API_KEY:
    raise RuntimeError("OPENAI_API_KEY is not set")

# Klient OpenAI (async, produkcyjny)
client = AsyncOpenAI(api_key=OPENAI_API_KEY)

SYSTEM_PROMPT = (
    "Popraw tekst nie zmieniając jego język, tekst ma być poprawiony pod względem stylistycznym i gramatycznym. "
    "Zachowaj znaczenie. "
    "Zwróć WYŁĄCZNIE poprawiony tekst, bez komentarzy, wyjaśnień ani formatowania."
)

SYSTEM_PROMPT2 = (
    "Masz przed sobą opis ofert pracy. "
    "Przeanalizuj go i wypisz w punktach czego w nim brakuje "
    "Wypisz tylko same punkty, bez żadnych dodatków."
)

async def improve_text(text: str) -> str:
    response = await client.chat.completions.create(
        model="gpt-4o-mini",
        messages=[
            {"role": "system", "content": SYSTEM_PROMPT},
            {"role": "user", "content": text},
        ],
        temperature=0.3,
    )

    return response.choices[0].message.content.strip()

async def find_gaps(text: str) -> str:
    response = await client.chat.completions.create(
        model="gpt-4o-mini",
        messages=[
            {"role": "system", "content": SYSTEM_PROMPT2},
            {"role": "user", "content": text},
        ],
        temperature=0.3,
    )

    return response.choices[0].message.content.strip()