#pragma once
#ifndef	type_h
#define	type_h 1.3
/** @file type.h
 * Convenient pseudo-types.
 */

// Warning: These psuedotypes are CPU and platform dependent!
// The comment tells what size they should be on your machine.
// If the actual sizes do not match, you may have to twiddle
// a bit with the choice of built-in types.
// For instance: "ulong" may have to be "unsigned int" on
// your machine if it has a 64 bit processor.

/// A one byte bit pattern
typedef unsigned char 		byte;
/// A two byte bit pattern
typedef	unsigned short		word;
/// A four byte bit pattern
typedef	unsigned int		quad;

/// A two byte unsigned number
typedef	unsigned short		ushort;
/// A four byte unsigned number
typedef	unsigned long		ulong;


#endif	/*type.h*/
// vim:sw=4:ts=4:ai:aw:
