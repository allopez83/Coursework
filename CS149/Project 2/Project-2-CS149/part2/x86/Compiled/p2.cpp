#include <windows.h>
#include <tchar.h>
#include <stdio.h>
#include <strsafe.h>
#include <string.h>
#include <algorithm>

#include <jni.h>
#include "p2.h"

struct fileItem {
    char * time;       // last modify time
    bool isFile;           // true for file, false for folder
    int size;            // bytes of file
    char * name;          // name of the item
    int pos;
};
fileItem dirContent[1000] = {0};

JNIEXPORT void JNICALL Java_p2_setDirContent
    (JNIEnv *env, jobject jobj) {
    WIN32_FIND_DATA ffd;
    LARGE_INTEGER filesize;
    char szDir[MAX_PATH];
    HANDLE hFind = INVALID_HANDLE_VALUE;
    DWORD dwError = 0;

    // Prepare string for use with FindFile functions.  First, copy the
    // string to a buffer, then append '\*' to the directory name.

    strcpy(szDir, "C://");
    strcat(szDir, "\\*");

    // Find the first file in the directory.

    hFind = FindFirstFile(szDir, &ffd);

    // List all the files in the directory with some info about them.
    int position = -1;
    do {
        position++;
        if (ffd.dwFileAttributes & FILE_ATTRIBUTE_DIRECTORY) {
            // printf("  %s     \t\t<DIR>\n", ffd.cFileName);
            dirContent[position].isFile = false;
            // printf("Found a dir\n");
        } else {
            if (!(ffd.dwFileAttributes & FILE_ATTRIBUTE_HIDDEN)) { /* don't print hidden files */
                filesize.LowPart = ffd.nFileSizeLow;
                filesize.HighPart = ffd.nFileSizeHigh;
                // printf("  %s     \t\t\t%ld bytes\n", ffd.cFileName, filesize.QuadPart);
                dirContent[position].isFile = true;
                dirContent[position].size = filesize.QuadPart;
                // printf("Found a file\n");
            }
        }
        // Name, same for dirs and files
        // dirContent[position].name = ffd.cFileName;
        // printf("copy");
        // strncpy(dirContent[position].name, ffd.cFileName, sizeof(ffd.cFileName));
        dirContent[position].name = (char *)malloc(strlen(ffd.cFileName)+1);
        strcpy(dirContent[position].name, ffd.cFileName);

        // File time

        char time[20];
        SYSTEMTIME stUTC, stLocal;
        FileTimeToSystemTime(&ffd.ftLastWriteTime, &stUTC);
        SystemTimeToTzSpecificLocalTime(NULL, &stUTC, &stLocal);
        sprintf(time, "%02d-%02d-%2d %02d:%02d", stLocal.wYear, stLocal.wMonth, stLocal.wDay, stLocal.wHour, stLocal.wMinute);

        dirContent[position].time = (char *)malloc(strlen(time)+1);
        strcpy(dirContent[position].time, time);
    }
    while (FindNextFile(hFind, &ffd) != 0);

    dirContent[0].pos = position;
}

// char** c_getName() {
//     static char* files[pos];
//     for(int i = 0; i < pos; i++) {
//         files[i] = dirContent[i].name;
//     }
//     return files;
// }
//
// char** c_getDate() {
//     static char* dates[pos];
//     for(int i = 0; i < pos; i++) {
//         files[i] = dirContent[i].time;
//     }
//     return dates;
// }
//
// bool* c_getIsFile() {
//     static bool isFile[pos];
//     for(int i = 0; i < pos; i++) {
//         files[i] = dirContent[i].isFile;
//     }
//     return isFile;
// }
//
// int* c_getSize() {
//     static int size[pos];
//     for(int i = 0; i < pos; i++) {
//         files[i] = dirContent[i].size;
//     }
//     return size;
// }

JNIEXPORT jobjectArray JNICALL Java_p2_getFileName
    (JNIEnv *env, jobject jobj){

    jobjectArray ret = {0};

    ret= (jobjectArray)env->NewObjectArray(dirContent[0].pos,
         env->FindClass("java/lang/String"),
         env->NewStringUTF(""));

    for(int i = 0; i < dirContent[0].pos; i++) {
        env->SetObjectArrayElement(
            ret,i,env->NewStringUTF(dirContent[i].name));
    }
    return ret;
}

JNIEXPORT jobjectArray JNICALL Java_p2_getDate
    (JNIEnv *env, jobject jobj){

    jobjectArray ret = {0};

    ret = (jobjectArray)env->NewObjectArray(dirContent[0].pos,
         env->FindClass("java/lang/String"),
         env->NewStringUTF(""));

    for(int i = 0; i < dirContent[0].pos; i++) {
        env->SetObjectArrayElement(
            ret,i,env->NewStringUTF(dirContent[i].time));
    }
    return ret;
}

JNIEXPORT jobjectArray JNICALL Java_p2_getIsFile
    (JNIEnv *env, jobject jobj) {

    // jbooleanArray ret = env->NewBooleanArray(dirContent[0].pos);
    //
    //
    //
    // for(int i = 0; i < dirContent[0].pos; i++) {
    //     env->SetBooleanArrayRegion(ret, (jsize) i, (jsize) i + 1, (const jboolean *) (dirContent[i].isFile));
    //     printf("%s", ((const jboolean *) (dirContent[i].isFile)));
    // }

    // ret = (jbooleanArray)env->NewObjectArray(dirContent[0].pos, env->FindClass("java/lang/Boolean"), false);
    //
    // // jboolean * body = {0};
    // // body = env->GetBooleanArrayElements(ret, 0);
    //
    // for(int i = 0; i < dirContent[0].pos; i++) {
    //     ret[i] = dirContent[i].isFile;
    // }
    //
    // env->ReleaseBooleanArrayElements(ret, ret, 0);

    jobjectArray ret = {0};

    ret = (jobjectArray)env->NewObjectArray(dirContent[0].pos,
         env->FindClass("java/lang/String"),
         env->NewStringUTF(""));

    for(int i = 0; i < dirContent[0].pos; i++) {
        if(dirContent[i].isFile) {
            env->SetObjectArrayElement(ret,i,env->NewStringUTF("true"));
        } else {
            env->SetObjectArrayElement(ret,i,env->NewStringUTF("false"));
        }
    }

    return ret;
}


JNIEXPORT jobjectArray JNICALL Java_p2_getFileSize
    (JNIEnv *env, jobject jobj) {

    // jintArray ret = {0};
    //
    // ret = (jintArray)env->NewObjectArray(dirContent[0].pos, env->FindClass("java/lang/Integer"), 0);
    //
    // jint * body = env->GetIntArrayElements(ret, 0);
    //
    // for(int i = 0; i < dirContent[0].pos; i++) {
    //     *(body + i) = dirContent[i].size;
    // }
    jobjectArray ret = {0};

    ret = (jobjectArray)env->NewObjectArray(dirContent[0].pos,
         env->FindClass("java/lang/String"),
         env->NewStringUTF(""));

    for(int i = 0; i < dirContent[0].pos; i++) {
        char buffer[10];
        sprintf(buffer, "%i", dirContent[i].size);
        env->SetObjectArrayElement(ret,i,env->NewStringUTF(buffer));
    }

    // env->ReleaseIntArrayElements(ret, body, 0);
    return ret;
}

void main(){}
