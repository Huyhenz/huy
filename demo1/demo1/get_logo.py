import urllib.request
from bs4 import BeautifulSoup
req = urllib.request.Request('https://www.thegioididong.com/', headers={'User-Agent': 'Mozilla/5.0'})
try:
    html = urllib.request.urlopen(req).read().decode('utf-8')
    soup = BeautifulSoup(html, 'html.parser')
    for img in soup.find_all('img'):
        src = img.get('src')
        if src and ('logo' in src.lower() or 'tgdd' in src.lower()):
            print("IMG:", src)
    for a in soup.find_all('a', class_='header__logo'):
        print("A:", a)
except Exception as e:
    print(e)
