# -*- coding: utf-8 -*-
"""
Created on Tue Apr 18 10:27:12 2017

@author: Peter
"""

import re
from bs4 import BeautifulSoup
import requests
import lxml.html
import pandas as pd
import csv


def getTitle(soup):
    title = soup.find_all("title")[0].text
    title = re.sub(r"on The Session", "", title)
    print("Title = ",title)
    return title

def getNumTunebooksRecordings(soup):
    numTunebooks = 0
    numRecordings = 0
    index = 0
    for ptext in soup.find_all('p', id=None, class_=None):
        if 'has been added' in ptext.text and index < 6:
            print('Tunebooks',ptext.text)
            numTunebooks = re.sub(r".*added\sto\s","", ptext.text);
            numTunebooks = re.sub(r"\stunebooks.*","", numTunebooks)
            numTunebooks = re.sub(r",","", numTunebooks)
            print('-->Tunebooks', numTunebooks)
        elif('There are' in ptext.text) and index < 6:
            #print('#recordings [',ptext,']', ptext.text)
            recordings = re.sub(r".*There\sare\s*", "", ptext.text)
            numRecordings = re.sub(r"recordings.*", "", recordings)
            numRecordings = re.sub(r"of\sa\stune.*", "", numRecordings)
            numRecordings = re.sub(r"\s*\n*", "", numRecordings)
            print("-->#recordings", numRecordings)
        index = index+1
    return [numTunebooks, str(numRecordings).rstrip()]         


def getAlsoKnownAs(soup):
    outputList = []
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
                outputList.append(aka.strip())
    return outputList

def getAllRecordings(soup):
    recordingNameLinks = []
    for ptext in soup.find_all('p', class_='info'):
        print('ptext',ptext)
        if re.search(r"recorded\stogether", ptext.text):
            recordedTogether = ptext.text
            print('recorded together:',recordedTogether)
            for recording in ptext.find_all("a"):
                print("recording",recording)
                print("recording link", recording.link)
                print("recording name", recording.string)
                recordingNameLinks.append(recording.string)
    return recordingNameLinks

def getSetting2Abc(soup):
    setting2abc = {}
    for setting in soup.find_all('div', class_='setting'):
        settingId = re.sub('setting','', setting.get('id'))
        print('setting id', settingId)
        abc = setting.find_all('div',class_='notes')[0].text
        print('\n',abc)
        setting2abc[settingId] = abc
    return setting2abc
        

def parseSessionTune(tuneId, outfid):
    url = "https://thesession.org/tunes/" + str(tuneId)
    data = requests.get(url).text
    print('parsing ',str(tuneId))

    with open('./tunes/session' + str(tuneId) + '.txt', 'w') as sessionfid:
        soup = BeautifulSoup(data, "lxml")
        title = getTitle(soup)
        print('Title:',title)
        [numTunebooks, numRecordings] = getNumTunebooksRecordings(soup)
        print('NumTunebooks',numTunebooks,'numRecordings',numRecordings)
        outfid.write('TuneId:\t' + str(tuneId) + "\nTitle:\t" + title + "\nNumTunebooks:\t" + numTunebooks + "\nNumRecordings\t" + numRecordings + "\n")
        akaList = getAlsoKnownAs(soup)
        for aka in akaList:
            print('aka',aka)
            aka = re.sub('\u010b', "-", aka)
            aka = re.sub('\u1e0b', "-", aka)
            aka = re.sub('\u1e40', "-", aka)
            aka = re.sub('\u2028', "-", aka)
            aka = re.sub('\x92'  , "-", aka)
            aka = re.sub('\ufffd', "-", aka)
            aka = re.sub('\u0301', "-", aka)
            aka = re.sub('\u010a', "-", aka)
            aka = re.sub('\u1e6b', "-", aka)
            aka = re.sub('\u0120', "-", aka)
            aka = re.sub('\u1e03', "-", aka)
            aka = re.sub('\u0121', "-", aka)
            aka = re.sub('\u1e02', "-", aka)
            aka = re.sub('\u1e61', "-", aka)
            aka = re.sub('\u1e41', "-", aka)
            aka = re.sub('\u1e1e', "-", aka)
            #outfid.write('aka:\t' + aka + "\n")
            outfid.write('aka:\t' + aka + "\n")
        recordingList = getAllRecordings(soup)
        for recording in recordingList:
            print('recording',recording)
            outfid.write('Rec:\t' + recording + "\n")
        setting2abc = getSetting2Abc(soup)
        for setting,abc in setting2abc.items():
            print('====================setting',setting)
            outfid.write('Set:\t' + setting + "\n")
            sessionfid.write('Tune ' + str(tuneId) + '\tSetting ' + setting + '\n')
            abc = re.sub(r"\n[\n\r\s]+","\n", abc)
            abc = re.sub('\u2028', "-", abc)

            sessionfid.write(abc + '\n\n')
            print(abc)
        outfid.write("\n")
        sessionfid.close()


#tuneId = 1
#url = "https://thesession.org/tunes/" + str(tuneId)
##data = requests.get(url).text
#print('parsing ',tuneId)
#soup = BeautifulSoup(data,"lxml")

def checkParseSessionTune(soup):
    #url = "https://thesession.org/tunes/" + str(tuneId)
    #data = requests.get(url).text
    #print('parsing ',tuneId)

        #soup = BeautifulSoup(data, "lxml")
    title = getTitle(soup)
    print("=======================================")
    print('Title:',title)
    [numTunebooks, numRecordings] = getNumTunebooksRecordings(soup)
    print("=======================================Recordings")
    print('NumTunebooks',numTunebooks,'numRecordings [',numRecordings,']')
    print("=======================================before AKA")
    akaList = getAlsoKnownAs(soup)
    for aka in akaList:
        print('aka',aka)
    recordingList = getAllRecordings(soup)
    print("=======================================AKA")
    for recording in recordingList:
        print('recording',recording)
    setting2abc = getSetting2Abc(soup)
    print("=======================================Setting")
    for setting,abc in setting2abc.items():
        print('====================setting',setting)
        #print(abc)
    print("=======================================Done")
        
#checkParseSessionTune(soup)


# Main routine loops through populatTunes3
startIndex = 371
endIndex = 476
with open('popularTunes3.txt','r') as csvfile, open('tuneData' + str(startIndex) + '.txt','w') as outfid:
    reader = csv.reader(csvfile, delimiter='\t')
    index = 0
    for row in reader:
        if index >= startIndex and index < endIndex:
            parseSessionTune(row[2], outfid)
        index += 1
            
csvfile.close()
outfid.close()
    
        
        
#parseSessionTune(181)
    