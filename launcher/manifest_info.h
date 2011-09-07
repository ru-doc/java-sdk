/*
 * @(#)manifest_info.h	1.15 10/03/23
 *
 * Копирайт (c) 2006, Oracle и/или его филиалы. Все права защищены.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Использовать в соответствии с лицензией.
 */

#ifndef _MANIFEST_INFO_H
#define	_MANIFEST_INFO_H

#include <sys/types.h>

/*
 * Сигнатуры заголовка zip-файла.
 */
#define	SIGSIZ 4		    /* размер всех сигнатур заголовка */
#define	LOCSIG 0x04034b50L	    /* "PK\003\004" */
#define	EXTSIG 0x08074b50L	    /* "PK\007\008" */
#define	CENSIG 0x02014b50L	    /* "PK\001\002" */
#define	ENDSIG 0x06054b50L	    /* "PK\005\006" */

/*
 * Размеры заголовков с сигнатурами.
 */
#define	LOCHDR 30
#define	EXTHDR 16
#define	CENHDR 46
#define	ENDHDR 22

/*
 * Макросы доступа к полям заголовка.
 */
#define	CH(b, n) (((unsigned char *)(b))[n])
#define	SH(b, n) (CH(b, n) | (CH(b, n+1) << 8))
#define	LG(b, n) (SH(b, n) | (SH(b, n+2) << 16))
#define	GETSIG(b) LG(b, 0)

/*
 * Макросы для получения локальных файловых (LOC) полей заголовка.
 */
#define	LOCVER(b) SH(b, 4)	    /* необходимая для извлечения версия */
#define	LOCFLG(b) SH(b, 6)	    /* битовые флаги общего назначения */
#define	LOCHOW(b) SH(b, 8)	    /* метод сжатия */
#define	LOCTIM(b) LG(b, 10)	    /* время модификации */
#define	LOCCRC(b) LG(b, 14)	    /* crc-сумма несжатых данных */
#define	LOCSIZ(b) LG(b, 18)	    /* размер сжатых данных */
#define	LOCLEN(b) LG(b, 22)	    /* размер несжатых данных */
#define	LOCNAM(b) SH(b, 26)	    /* длина имени файла */
#define	LOCEXT(b) SH(b, 28)	    /* длина дополнительного поля */

/*
 * Макросы для получения дополнительных локальных (EXT) полей заголовка.
 */
#define	EXTCRC(b) LG(b, 4)	    /* crc-сумма несжатых данных */
#define	EXTSIZ(b) LG(b, 8)	    /* размер сжатых данных */
#define	EXTLEN(b) LG(b, 12)	    /* размер несжатых данных */

/*
 * Макросы для получения полей центрального заголовка каталога (CEN).
 */
#define	CENVEM(b) SH(b, 4)	    /* версия, с помощью которой был создан файл */
#define	CENVER(b) SH(b, 6)	    /* версия, необходимая для извлечения */
#define	CENFLG(b) SH(b, 8)	    /* битовые флаги общего назначения */
#define	CENHOW(b) SH(b, 10)	    /* метод сжатия */
#define	CENTIM(b) LG(b, 12)	    /* время модификации */
#define	CENCRC(b) LG(b, 16)	    /* crc-сумма несжатых данных */
#define	CENSIZ(b) LG(b, 20)	    /* размер сжатых данных */
#define	CENLEN(b) LG(b, 24)	    /* размер несжатых данных */
#define	CENNAM(b) SH(b, 28)	    /* длина имени файла */
#define	CENEXT(b) SH(b, 30)	    /* длина дополнительного поля */
#define	CENCOM(b) SH(b, 32)	    /* длина комментария к файлу */
#define	CENDSK(b) SH(b, 34)	    /* номер начального диска */
#define	CENATT(b) SH(b, 36)	    /* внутренние атрибуты файла */
#define	CENATX(b) LG(b, 38)	    /* внешние атрибуты файла */
#define	CENOFF(b) LG(b, 42)	    /* смещение локального заголовка */

/*
 * Макросы для получения полей конца центрального заголовка каталога (END).
 */
#define	ENDSUB(b) SH(b, 8)	    /* количество записей на этом диске */
#define	ENDTOT(b) SH(b, 10)	    /* общее количество записей */
#define	ENDSIZ(b) LG(b, 12)	    /* размер центрального каталога */
#define	ENDOFF(b) LG(b, 16)	    /* смещение центрального каталога */
#define	ENDCOM(b) SH(b, 20)	    /* размер комментария zip-файла */

/*
 * За записью END может следовать комментарий максимальной длины в 64Кб. Это
 * самая дальняя запись END может быть в конце файла.
 */
#define	END_MAXLEN	(0xFFFF + ENDHDR)

/*
 * Поддерживаемые методы сжатия.
 */
#define	STORED	    0
#define	DEFLATED    8

/*
 * Информация из записи CEN для распаковываемого файла.
 */
typedef struct zentry {	/* запись zip-файла */
    size_t	isize;	/* размер распакованных данных */
    size_t	csize;	/* размер сжатых данных (ноль, если несжаты) */
    off_t	offset;	/* положение сжатых данных */
    int		how;	/* метод сжатия (если есть) */
} zentry;

/*
 * Информация, возвращаемая из файла манифеста Manifest вызовом подпрограммы ParseManifest().
 * Конечно, будет возвращено ещё (очень) много другой информации, но эта информация
 * интересна с точки зрения C-утилит Java (особенно
 * запускателю Java).
 */
typedef struct manifest_info {	/* Интересующие поля из манифеста */
    char	*manifest_version;	/* строка Manifest-Version */
    char	*main_class;		/* запись Main-Class */
    char	*jre_version;		/* Соответствующая спецификация выпуска J2SE */
    char	jre_restrict_search;	/* Ограниченный поиск JRE */
    char	*splashscreen_image_file_name; /* файл изображения начальной заставки */
} manifest_info;

/*
 * Атрибут замыкания предоставляется для manifest_iterate.
 */
typedef void (*attribute_closure)(const char *name, const char *value,
	void *user_data);

/*
 * Прототипы функций.
 */
int	JLI_ParseManifest(char *jarfile, manifest_info *info);
void	*JLI_JarUnpackFile(const char *jarfile, const char *filename,
		int *size);
void	JLI_FreeManifest(void);
int	JLI_ManifestIterate(const char *jarfile, attribute_closure ac,
		void *user_data);

#endif	/* _MANIFEST_INFO_H */
