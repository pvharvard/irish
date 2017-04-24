# -*- coding: utf-8 -*-
"""
Created on Tue Apr 18 10:27:12 2017

@author: Peter
"""

import re
from bs4 import BeautifulSoup
import requests
import lxml.html

url = "https://thesession.org/tunes/118"  #Haunted House
data = requests.get(url).text
#print(data)

soup = BeautifulSoup(data, "lxml")

title = soup.find_all("title")[0].text
print("Title = ",title)

soup.find_all(name="abc")


ptext = soup.find_all('p', class_="info")  # Also known as

for ptext in soup.find_all('p', id=None, class_=None):
    if 'has been added' in ptext.text:
        print('Tunebooks',ptext.text)
        numTunebooks = re.sub(r".*added\sto\s","", ptext.text);
        numTunebooks = re.sub(r"\stunebooks.*","", numTunebooks)
        print('-->Tunebooks', numTunebooks)
    elif('There are' in ptext.text):
        #print('#recordings [',ptext,']', ptext.text)
        recordings = re.sub(r".*There\sare\s*", "", ptext.text)
        recordings = re.sub(r"recordings.*", "", recordings)
        print("-->#recordings", recordings)

for ptext in soup.find_all('p', class_='info'):
    print('ptext',ptext)
    if re.search(r"Also\sknown", ptext.text):
        alsoKnown = re.sub(r"Also\s*known\s+as\s*","", ptext.text)
        print("Alsoknown1",alsoKnown)
        alsoKnown = re.sub(r"\.\s*$","", alsoKnown)
        print("Alsoknown2",alsoKnown)
        alsoKnownList = alsoKnown.split(",")
        for aka in alsoKnownList:
            print('\talso known:',aka.strip())
    if re.search(r"recorded\stogether", ptext.text):
        recordedTogether = ptext.text
        print('recorded together:',recordedTogether)
        for recording in ptext.find_all("a"):
            print("recording",recording)
            print("recording link", recording.link)
            print("recording name", recording.string)
        
            
for setting in soup.find_all('div', class_='setting'):
        settingId = re.sub('setting','', setting.get('id'))
        print('setting id', settingId)
        #print("setting notes", setting.notes.text)
        abc = setting.find_all('div',class_='notes')[0].text
        print('\n',abc)
        
    
        
        
    