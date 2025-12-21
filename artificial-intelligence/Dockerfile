# Używamy oficjalnego Pythona
FROM python:3.13-slim

# Ustaw katalog roboczy
WORKDIR /app

# Kopiujemy wymagania i instalujemy zależności
COPY requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt

# Kopiujemy całą aplikację
COPY . .

# Ustawienie zmiennej środowiskowej dla OpenAI (alternatywnie można w K8s Secret)
# ENV OPENAI_API_KEY=sk-xxxx

# Uruchomienie Uvicorna
CMD ["uvicorn", "app.main:app", "--host", "0.0.0.0", "--port", "8000"]
